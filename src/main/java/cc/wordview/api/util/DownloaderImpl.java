/*
 * Copyright (c) 2025 Arthur Araujo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package cc.wordview.api.util;

import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Request;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public final class DownloaderImpl extends Downloader {
        public static final String USER_AGENT =
                "Mozilla/5.0 (Windows NT 10.0; rv:91.0) Gecko/20100101 Firefox/91.0";
        public static final String YOUTUBE_RESTRICTED_MODE_COOKIE_KEY =
                "youtube_restricted_mode_key";
        public static final String YOUTUBE_RESTRICTED_MODE_COOKIE = "PREF=f2=8000000";
        public static final String YOUTUBE_DOMAIN = "youtube.com";

        private static DownloaderImpl instance;
        private final Map<String, String> mCookies;
        private final OkHttpClient client;

        private DownloaderImpl(final OkHttpClient.Builder builder) {
                this.client = builder
                        .readTimeout(30, TimeUnit.SECONDS)
//                .cache(new Cache(new File(context.getExternalCacheDir(), "okhttp"),
//                        16 * 1024 * 1024))
                        .build();
                this.mCookies = new HashMap<>();
        }

        /**
         * It's recommended to call exactly once in the entire lifetime of the application.
         *
         * @param builder if null, default builder will be used
         * @return a new instance of {@link DownloaderImpl}
         */
        public static DownloaderImpl init(final OkHttpClient.Builder builder) {
                instance = new DownloaderImpl(
                        builder != null ? builder : new OkHttpClient.Builder());
                return instance;
        }

        public static DownloaderImpl getInstance() {
                return instance;
        }

        public String getCookies(final String url) {
                final String youtubeCookie = url.contains(YOUTUBE_DOMAIN)
                        ? getCookie(YOUTUBE_RESTRICTED_MODE_COOKIE_KEY) : null;

                return Stream.of(youtubeCookie)
                        .filter(Objects::nonNull)
                        .flatMap(cookies -> Arrays.stream(cookies.split("; *")))
                        .distinct()
                        .collect(Collectors.joining("; "));
        }

        public String getCookie(final String key) {
                return mCookies.get(key);
        }

        public void setCookie(final String key, final String cookie) {
                mCookies.put(key, cookie);
        }

        public void removeCookie(final String key) {
                mCookies.remove(key);
        }

        /**
         * Get the size of the content that the url is pointing by firing a HEAD request.
         *
         * @param url an url pointing to the content
         * @return the size of the content, in bytes
         */
        public long getContentLength(final String url) throws IOException {
                try {
                        final Response response = head(url);
                        return Long.parseLong(response.getHeader("Content-Length"));
                } catch (final NumberFormatException e) {
                        throw new IOException("Invalid content length", e);
                } catch (final ReCaptchaException e) {
                        throw new IOException(e);
                }
        }

        @Override
        public Response execute(final Request request)
                throws IOException, ReCaptchaException {
                final String httpMethod = request.httpMethod();
                final String url = request.url();
                final Map<String, List<String>> headers = request.headers();
                final byte[] dataToSend = request.dataToSend();

                RequestBody requestBody = null;
                if (dataToSend != null) {
                        requestBody = RequestBody.create(dataToSend);
                }

                final okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder()
                        .method(httpMethod, requestBody).url(url)
                        .addHeader("User-Agent", USER_AGENT);

                final String cookies = getCookies(url);
                if (!cookies.isEmpty()) {
                        requestBuilder.addHeader("Cookie", cookies);
                }

                for (final Map.Entry<String, List<String>> pair : headers.entrySet()) {
                        final String headerName = pair.getKey();
                        final List<String> headerValueList = pair.getValue();

                        if (headerValueList.size() > 1) {
                                requestBuilder.removeHeader(headerName);
                                for (final String headerValue : headerValueList) {
                                        requestBuilder.addHeader(headerName, headerValue);
                                }
                        } else if (headerValueList.size() == 1) {
                                requestBuilder.header(headerName, headerValueList.get(0));
                        }

                }

                final okhttp3.Response response = client.newCall(requestBuilder.build()).execute();

                if (response.code() == 429) {
                        response.close();

                        throw new ReCaptchaException("reCaptcha Challenge requested", url);
                }

                final ResponseBody body = response.body();
                String responseBodyToReturn = null;

                if (body != null) {
                        responseBodyToReturn = body.string();
                }

                final String latestUrl = response.request().url().toString();
                return new Response(response.code(), response.message(), response.headers().toMultimap(),
                        responseBodyToReturn, latestUrl);
        }
}