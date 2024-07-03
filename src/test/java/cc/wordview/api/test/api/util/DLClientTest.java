/*
 * Copyright (c) 2024 Arthur Araujo
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

package cc.wordview.api.test.api.util;

import cc.wordview.api.service.util.DLClient;
import cc.wordview.api.util.FileReader;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class DLClientTest {
    @Test
    void downloadSubtitle() throws Exception {
        assertDoesNotThrow(() -> {
            DLClient.downloadSubtitle("gqiRJn7me-s", "ja");
            String subtitle = FileReader.read("/tmp/gqiRJn7me-s.ja.vtt");
            assertEquals(actualSubtitle, subtitle);
        });
    }

    @Test
    void listSubtitles() throws Exception {
        assertDoesNotThrow(() -> {
            String subtitles = DLClient.listSubtitles("gqiRJn7me-s");
            assertEquals(actualSubtitleList, subtitles);
        });
    }

    @Test
    void downloadVideo() throws Exception {
        assertDoesNotThrow(() -> {
            DLClient.downloadVideo("gqiRJn7me-s");
            Path file = Paths.get( "/tmp/gqiRJn7me-s.mp3");
            assertTrue(Files.exists(file));
        });
    }

    @Test
    void search() throws Exception {
        assertDoesNotThrow(() -> {
            String result = DLClient.search("pandora101", 1);
            assertNotNull(result);
        });
    }

    private static final String actualSubtitle = "WEBVTT\n" +
            "Kind: captions\n" +
            "Language: ja\n" +
            "\n" +
            "00:00:03.500 --> 00:00:12.980\n" +
            " \n" +
            "\n" +
            "00:00:14.080 --> 00:00:18.900\n" +
            "消えそうな星を眺めて\n" +
            "\n" +
            "00:00:19.160 --> 00:00:24.860\n" +
            "憂いを夜空に吐き出す\n" +
            "\n" +
            "00:00:25.420 --> 00:00:30.200\n" +
            "落ちる一筋の願いは\n" +
            "\n" +
            "00:00:30.420 --> 00:00:35.780\n" +
            "淡く溶けて消えた\n" +
            "\n" +
            "00:00:36.580 --> 00:00:41.840\n" +
            "君のいつもの口癖も\n" +
            "\n" +
            "00:00:42.080 --> 00:00:47.520\n" +
            "負けず嫌いな性格も\n" +
            "\n" +
            "00:00:47.700 --> 00:00:52.860\n" +
            "隣で笑うその顔も\n" +
            "\n" +
            "00:00:53.560 --> 00:00:58.280\n" +
            "大事にしてたのに\n" +
            "\n" +
            "00:00:59.440 --> 00:01:04.640\n" +
            "気付けば何故か言えなくなった\n" +
            "\n" +
            "00:01:04.860 --> 00:01:10.740\n" +
            "君に向けた「好き」という言葉\n" +
            "\n" +
            "00:01:11.140 --> 00:01:16.380\n" +
            "心の何処かでは諦めてたのかな\n" +
            "\n" +
            "00:01:16.480 --> 00:01:22.000\n" +
            "戻れないと知ってても\n" +
            "\n" +
            "00:01:22.248 --> 00:01:27.628\n" +
            "二人の過去を全て壊して\n" +
            "\n" +
            "00:01:28.148 --> 00:01:33.228\n" +
            "もう僕なんか嫌いになってよ\n" +
            "\n" +
            "00:01:33.528 --> 00:01:39.148\n" +
            "そしたら君の事を今すぐにでも\n" +
            "\n" +
            "00:01:39.352 --> 00:01:44.732\n" +
            "忘れてしまえるのに\n" +
            "\n" +
            "00:01:56.768 --> 00:02:01.828\n" +
            "僕の最高の思い出を\n" +
            "\n" +
            "00:02:02.120 --> 00:02:07.420\n" +
            "胸の中にまた隠して\n" +
            "\n" +
            "00:02:08.300 --> 00:02:13.460\n" +
            "先の見えないこの道を\n" +
            "\n" +
            "00:02:13.696 --> 00:02:18.016\n" +
            "今日も一人歩く\n" +
            "\n" +
            "00:02:19.556 --> 00:02:24.516\n" +
            "いつしか何も信じられない\n" +
            "\n" +
            "00:02:25.100 --> 00:02:30.620\n" +
            "そんな自分がこの場所にいた\n" +
            "\n" +
            "00:02:30.728 --> 00:02:36.188\n" +
            "離れたくないけど僕はもう行かなきゃ\n" +
            "\n" +
            "00:02:36.360 --> 00:02:41.960\n" +
            "帰れないと知ってても\n" +
            "\n" +
            "00:02:42.240 --> 00:02:47.660\n" +
            "愛した日々もその温もりも\n" +
            "\n" +
            "00:02:47.708 --> 00:02:53.328\n" +
            "抱きしめ合って過ごした夜も\n" +
            "\n" +
            "00:02:53.524 --> 00:02:59.304\n" +
            "嘘じゃないと自分に言い聞かせて\n" +
            "\n" +
            "00:02:59.484 --> 00:03:04.664\n" +
            "逃げていたいだけなんだよ\n" +
            "\n" +
            "00:03:07.904 --> 00:03:13.464\n" +
            "二人の過去を全て壊して\n" +
            "\n" +
            "00:03:13.584 --> 00:03:19.324\n" +
            "もう僕なんか嫌いになってよ\n" +
            "\n" +
            "00:03:19.504 --> 00:03:24.804\n" +
            "そしたら君の事を今すぐにでも\n" +
            "\n" +
            "00:03:24.964 --> 00:03:30.644\n" +
            "忘れてしまえるのに\n" +
            "\n" +
            "00:03:54.136 --> 00:03:58.756\n" +
            "君に最後の口づけを\n" +
            "\n" +
            "00:03:59.280 --> 00:04:04.580\n" +
            "今まで本当にありがとう\n" +
            "\n" +
            "00:04:05.460 --> 00:04:10.160\n" +
            "重なる二人の想いは\n" +
            "\n" +
            "00:04:10.580 --> 00:04:15.400\n" +
            "涙の味がした\n" +
            "\n";

    private static final String actualSubtitleList = "[youtube] Extracting URL: https://www.youtube.com/watch?v=gqiRJn7me-s\n" +
            "[youtube] gqiRJn7me-s: Downloading webpage\n" +
            "[youtube] gqiRJn7me-s: Downloading ios player API JSON\n" +
            "[youtube] gqiRJn7me-s: Downloading android player API JSON\n" +
            "[youtube] gqiRJn7me-s: Downloading m3u8 information\n" +
            "[info] Available automatic captions for gqiRJn7me-s:\n" +
            "Language        Name                                             Formats\n" +
            "zh-Hans                                                          vtt\n" +
            "zh-Hant                                                          vtt\n" +
            "en                                                               vtt\n" +
            "ja                                                               vtt\n" +
            "af-zh-Hans      Afrikaans from Chinese (Simplified)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ak-zh-Hans      Akan from Chinese (Simplified)                   vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sq-zh-Hans      Albanian from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "am-zh-Hans      Amharic from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ar-zh-Hans      Arabic from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hy-zh-Hans      Armenian from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "as-zh-Hans      Assamese from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ay-zh-Hans      Aymara from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "az-zh-Hans      Azerbaijani from Chinese (Simplified)            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bn-zh-Hans      Bangla from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "eu-zh-Hans      Basque from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "be-zh-Hans      Belarusian from Chinese (Simplified)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bho-zh-Hans     Bhojpuri from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bs-zh-Hans      Bosnian from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bg-zh-Hans      Bulgarian from Chinese (Simplified)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "my-zh-Hans      Burmese from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ca-zh-Hans      Catalan from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ceb-zh-Hans     Cebuano from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zh-Hans-zh-Hans Chinese (Simplified) from Chinese (Simplified)   vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zh-Hant-zh-Hans Chinese (Traditional) from Chinese (Simplified)  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "co-zh-Hans      Corsican from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hr-zh-Hans      Croatian from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "cs-zh-Hans      Czech from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "da-zh-Hans      Danish from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "dv-zh-Hans      Divehi from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "nl-zh-Hans      Dutch from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "en-zh-Hans      English from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "eo-zh-Hans      Esperanto from Chinese (Simplified)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "et-zh-Hans      Estonian from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ee-zh-Hans      Ewe from Chinese (Simplified)                    vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fil-zh-Hans     Filipino from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fi-zh-Hans      Finnish from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fr-zh-Hans      French from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gl-zh-Hans      Galician from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lg-zh-Hans      Ganda from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ka-zh-Hans      Georgian from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "de-zh-Hans      German from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "el-zh-Hans      Greek from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gn-zh-Hans      Guarani from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gu-zh-Hans      Gujarati from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ht-zh-Hans      Haitian Creole from Chinese (Simplified)         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ha-zh-Hans      Hausa from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "haw-zh-Hans     Hawaiian from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "iw-zh-Hans      Hebrew from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hi-zh-Hans      Hindi from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hmn-zh-Hans     Hmong from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hu-zh-Hans      Hungarian from Chinese (Simplified)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "is-zh-Hans      Icelandic from Chinese (Simplified)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ig-zh-Hans      Igbo from Chinese (Simplified)                   vtt, ttml, srv3, srv2, srv1, json3\n" +
            "id-zh-Hans      Indonesian from Chinese (Simplified)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ga-zh-Hans      Irish from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "it-zh-Hans      Italian from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ja-zh-Hans      Japanese from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "jv-zh-Hans      Javanese from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kn-zh-Hans      Kannada from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kk-zh-Hans      Kazakh from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "km-zh-Hans      Khmer from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "rw-zh-Hans      Kinyarwanda from Chinese (Simplified)            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ko-zh-Hans      Korean from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kri-zh-Hans     Krio from Chinese (Simplified)                   vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ku-zh-Hans      Kurdish from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ky-zh-Hans      Kyrgyz from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lo-zh-Hans      Lao from Chinese (Simplified)                    vtt, ttml, srv3, srv2, srv1, json3\n" +
            "la-zh-Hans      Latin from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lv-zh-Hans      Latvian from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ln-zh-Hans      Lingala from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lt-zh-Hans      Lithuanian from Chinese (Simplified)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lb-zh-Hans      Luxembourgish from Chinese (Simplified)          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mk-zh-Hans      Macedonian from Chinese (Simplified)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mg-zh-Hans      Malagasy from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ms-zh-Hans      Malay from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ml-zh-Hans      Malayalam from Chinese (Simplified)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mt-zh-Hans      Maltese from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mi-zh-Hans      MÄ\u0081ori from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mr-zh-Hans      Marathi from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mn-zh-Hans      Mongolian from Chinese (Simplified)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ne-zh-Hans      Nepali from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "nso-zh-Hans     Northern Sotho from Chinese (Simplified)         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "no-zh-Hans      Norwegian from Chinese (Simplified)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ny-zh-Hans      Nyanja from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "or-zh-Hans      Odia from Chinese (Simplified)                   vtt, ttml, srv3, srv2, srv1, json3\n" +
            "om-zh-Hans      Oromo from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ps-zh-Hans      Pashto from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fa-zh-Hans      Persian from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pl-zh-Hans      Polish from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pt-zh-Hans      Portuguese from Chinese (Simplified)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pa-zh-Hans      Punjabi from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "qu-zh-Hans      Quechua from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ro-zh-Hans      Romanian from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ru-zh-Hans      Russian from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sm-zh-Hans      Samoan from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sa-zh-Hans      Sanskrit from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gd-zh-Hans      Scottish Gaelic from Chinese (Simplified)        vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sr-zh-Hans      Serbian from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sn-zh-Hans      Shona from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sd-zh-Hans      Sindhi from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "si-zh-Hans      Sinhala from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sk-zh-Hans      Slovak from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sl-zh-Hans      Slovenian from Chinese (Simplified)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "so-zh-Hans      Somali from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "st-zh-Hans      Southern Sotho from Chinese (Simplified)         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "es-zh-Hans      Spanish from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "su-zh-Hans      Sundanese from Chinese (Simplified)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sw-zh-Hans      Swahili from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sv-zh-Hans      Swedish from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tg-zh-Hans      Tajik from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ta-zh-Hans      Tamil from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tt-zh-Hans      Tatar from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "te-zh-Hans      Telugu from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "th-zh-Hans      Thai from Chinese (Simplified)                   vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ti-zh-Hans      Tigrinya from Chinese (Simplified)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ts-zh-Hans      Tsonga from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tr-zh-Hans      Turkish from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tk-zh-Hans      Turkmen from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "uk-zh-Hans      Ukrainian from Chinese (Simplified)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ur-zh-Hans      Urdu from Chinese (Simplified)                   vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ug-zh-Hans      Uyghur from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "uz-zh-Hans      Uzbek from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "vi-zh-Hans      Vietnamese from Chinese (Simplified)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "cy-zh-Hans      Welsh from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fy-zh-Hans      Western Frisian from Chinese (Simplified)        vtt, ttml, srv3, srv2, srv1, json3\n" +
            "xh-zh-Hans      Xhosa from Chinese (Simplified)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "yi-zh-Hans      Yiddish from Chinese (Simplified)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "yo-zh-Hans      Yoruba from Chinese (Simplified)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zu-zh-Hans      Zulu from Chinese (Simplified)                   vtt, ttml, srv3, srv2, srv1, json3\n" +
            "af-zh-Hant      Afrikaans from Chinese (Traditional)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ak-zh-Hant      Akan from Chinese (Traditional)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sq-zh-Hant      Albanian from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "am-zh-Hant      Amharic from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ar-zh-Hant      Arabic from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hy-zh-Hant      Armenian from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "as-zh-Hant      Assamese from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ay-zh-Hant      Aymara from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "az-zh-Hant      Azerbaijani from Chinese (Traditional)           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bn-zh-Hant      Bangla from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "eu-zh-Hant      Basque from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "be-zh-Hant      Belarusian from Chinese (Traditional)            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bho-zh-Hant     Bhojpuri from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bs-zh-Hant      Bosnian from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bg-zh-Hant      Bulgarian from Chinese (Traditional)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "my-zh-Hant      Burmese from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ca-zh-Hant      Catalan from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ceb-zh-Hant     Cebuano from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zh-Hans-zh-Hant Chinese (Simplified) from Chinese (Traditional)  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zh-Hant-zh-Hant Chinese (Traditional) from Chinese (Traditional) vtt, ttml, srv3, srv2, srv1, json3\n" +
            "co-zh-Hant      Corsican from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hr-zh-Hant      Croatian from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "cs-zh-Hant      Czech from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "da-zh-Hant      Danish from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "dv-zh-Hant      Divehi from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "nl-zh-Hant      Dutch from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "en-zh-Hant      English from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "eo-zh-Hant      Esperanto from Chinese (Traditional)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "et-zh-Hant      Estonian from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ee-zh-Hant      Ewe from Chinese (Traditional)                   vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fil-zh-Hant     Filipino from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fi-zh-Hant      Finnish from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fr-zh-Hant      French from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gl-zh-Hant      Galician from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lg-zh-Hant      Ganda from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ka-zh-Hant      Georgian from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "de-zh-Hant      German from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "el-zh-Hant      Greek from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gn-zh-Hant      Guarani from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gu-zh-Hant      Gujarati from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ht-zh-Hant      Haitian Creole from Chinese (Traditional)        vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ha-zh-Hant      Hausa from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "haw-zh-Hant     Hawaiian from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "iw-zh-Hant      Hebrew from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hi-zh-Hant      Hindi from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hmn-zh-Hant     Hmong from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hu-zh-Hant      Hungarian from Chinese (Traditional)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "is-zh-Hant      Icelandic from Chinese (Traditional)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ig-zh-Hant      Igbo from Chinese (Traditional)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "id-zh-Hant      Indonesian from Chinese (Traditional)            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ga-zh-Hant      Irish from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "it-zh-Hant      Italian from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ja-zh-Hant      Japanese from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "jv-zh-Hant      Javanese from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kn-zh-Hant      Kannada from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kk-zh-Hant      Kazakh from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "km-zh-Hant      Khmer from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "rw-zh-Hant      Kinyarwanda from Chinese (Traditional)           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ko-zh-Hant      Korean from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kri-zh-Hant     Krio from Chinese (Traditional)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ku-zh-Hant      Kurdish from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ky-zh-Hant      Kyrgyz from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lo-zh-Hant      Lao from Chinese (Traditional)                   vtt, ttml, srv3, srv2, srv1, json3\n" +
            "la-zh-Hant      Latin from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lv-zh-Hant      Latvian from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ln-zh-Hant      Lingala from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lt-zh-Hant      Lithuanian from Chinese (Traditional)            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lb-zh-Hant      Luxembourgish from Chinese (Traditional)         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mk-zh-Hant      Macedonian from Chinese (Traditional)            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mg-zh-Hant      Malagasy from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ms-zh-Hant      Malay from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ml-zh-Hant      Malayalam from Chinese (Traditional)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mt-zh-Hant      Maltese from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mi-zh-Hant      MÄ\u0081ori from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mr-zh-Hant      Marathi from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mn-zh-Hant      Mongolian from Chinese (Traditional)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ne-zh-Hant      Nepali from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "nso-zh-Hant     Northern Sotho from Chinese (Traditional)        vtt, ttml, srv3, srv2, srv1, json3\n" +
            "no-zh-Hant      Norwegian from Chinese (Traditional)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ny-zh-Hant      Nyanja from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "or-zh-Hant      Odia from Chinese (Traditional)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "om-zh-Hant      Oromo from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ps-zh-Hant      Pashto from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fa-zh-Hant      Persian from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pl-zh-Hant      Polish from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pt-zh-Hant      Portuguese from Chinese (Traditional)            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pa-zh-Hant      Punjabi from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "qu-zh-Hant      Quechua from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ro-zh-Hant      Romanian from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ru-zh-Hant      Russian from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sm-zh-Hant      Samoan from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sa-zh-Hant      Sanskrit from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gd-zh-Hant      Scottish Gaelic from Chinese (Traditional)       vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sr-zh-Hant      Serbian from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sn-zh-Hant      Shona from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sd-zh-Hant      Sindhi from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "si-zh-Hant      Sinhala from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sk-zh-Hant      Slovak from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sl-zh-Hant      Slovenian from Chinese (Traditional)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "so-zh-Hant      Somali from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "st-zh-Hant      Southern Sotho from Chinese (Traditional)        vtt, ttml, srv3, srv2, srv1, json3\n" +
            "es-zh-Hant      Spanish from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "su-zh-Hant      Sundanese from Chinese (Traditional)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sw-zh-Hant      Swahili from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sv-zh-Hant      Swedish from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tg-zh-Hant      Tajik from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ta-zh-Hant      Tamil from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tt-zh-Hant      Tatar from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "te-zh-Hant      Telugu from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "th-zh-Hant      Thai from Chinese (Traditional)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ti-zh-Hant      Tigrinya from Chinese (Traditional)              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ts-zh-Hant      Tsonga from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tr-zh-Hant      Turkish from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tk-zh-Hant      Turkmen from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "uk-zh-Hant      Ukrainian from Chinese (Traditional)             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ur-zh-Hant      Urdu from Chinese (Traditional)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ug-zh-Hant      Uyghur from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "uz-zh-Hant      Uzbek from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "vi-zh-Hant      Vietnamese from Chinese (Traditional)            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "cy-zh-Hant      Welsh from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fy-zh-Hant      Western Frisian from Chinese (Traditional)       vtt, ttml, srv3, srv2, srv1, json3\n" +
            "xh-zh-Hant      Xhosa from Chinese (Traditional)                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "yi-zh-Hant      Yiddish from Chinese (Traditional)               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "yo-zh-Hant      Yoruba from Chinese (Traditional)                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zu-zh-Hant      Zulu from Chinese (Traditional)                  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "af-en           Afrikaans from English                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ak-en           Akan from English                                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sq-en           Albanian from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "am-en           Amharic from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ar-en           Arabic from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hy-en           Armenian from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "as-en           Assamese from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ay-en           Aymara from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "az-en           Azerbaijani from English                         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bn-en           Bangla from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "eu-en           Basque from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "be-en           Belarusian from English                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bho-en          Bhojpuri from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bs-en           Bosnian from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bg-en           Bulgarian from English                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "my-en           Burmese from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ca-en           Catalan from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ceb-en          Cebuano from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zh-Hans-en      Chinese (Simplified) from English                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zh-Hant-en      Chinese (Traditional) from English               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "co-en           Corsican from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hr-en           Croatian from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "cs-en           Czech from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "da-en           Danish from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "dv-en           Divehi from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "nl-en           Dutch from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "en-en           English from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "eo-en           Esperanto from English                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "et-en           Estonian from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ee-en           Ewe from English                                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fil-en          Filipino from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fi-en           Finnish from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fr-en           French from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gl-en           Galician from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lg-en           Ganda from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ka-en           Georgian from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "de-en           German from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "el-en           Greek from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gn-en           Guarani from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gu-en           Gujarati from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ht-en           Haitian Creole from English                      vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ha-en           Hausa from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "haw-en          Hawaiian from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "iw-en           Hebrew from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hi-en           Hindi from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hmn-en          Hmong from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hu-en           Hungarian from English                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "is-en           Icelandic from English                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ig-en           Igbo from English                                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "id-en           Indonesian from English                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ga-en           Irish from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "it-en           Italian from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ja-en           Japanese from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "jv-en           Javanese from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kn-en           Kannada from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kk-en           Kazakh from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "km-en           Khmer from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "rw-en           Kinyarwanda from English                         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ko-en           Korean from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kri-en          Krio from English                                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ku-en           Kurdish from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ky-en           Kyrgyz from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lo-en           Lao from English                                 vtt, ttml, srv3, srv2, srv1, json3\n" +
            "la-en           Latin from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lv-en           Latvian from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ln-en           Lingala from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lt-en           Lithuanian from English                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lb-en           Luxembourgish from English                       vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mk-en           Macedonian from English                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mg-en           Malagasy from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ms-en           Malay from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ml-en           Malayalam from English                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mt-en           Maltese from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mi-en           MÄ\u0081ori from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mr-en           Marathi from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mn-en           Mongolian from English                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ne-en           Nepali from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "nso-en          Northern Sotho from English                      vtt, ttml, srv3, srv2, srv1, json3\n" +
            "no-en           Norwegian from English                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ny-en           Nyanja from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "or-en           Odia from English                                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "om-en           Oromo from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ps-en           Pashto from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fa-en           Persian from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pl-en           Polish from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pt-en           Portuguese from English                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pa-en           Punjabi from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "qu-en           Quechua from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ro-en           Romanian from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ru-en           Russian from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sm-en           Samoan from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sa-en           Sanskrit from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gd-en           Scottish Gaelic from English                     vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sr-en           Serbian from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sn-en           Shona from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sd-en           Sindhi from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "si-en           Sinhala from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sk-en           Slovak from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sl-en           Slovenian from English                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "so-en           Somali from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "st-en           Southern Sotho from English                      vtt, ttml, srv3, srv2, srv1, json3\n" +
            "es-en           Spanish from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "su-en           Sundanese from English                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sw-en           Swahili from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sv-en           Swedish from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tg-en           Tajik from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ta-en           Tamil from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tt-en           Tatar from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "te-en           Telugu from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "th-en           Thai from English                                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ti-en           Tigrinya from English                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ts-en           Tsonga from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tr-en           Turkish from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tk-en           Turkmen from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "uk-en           Ukrainian from English                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ur-en           Urdu from English                                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ug-en           Uyghur from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "uz-en           Uzbek from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "vi-en           Vietnamese from English                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "cy-en           Welsh from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fy-en           Western Frisian from English                     vtt, ttml, srv3, srv2, srv1, json3\n" +
            "xh-en           Xhosa from English                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "yi-en           Yiddish from English                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "yo-en           Yoruba from English                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zu-en           Zulu from English                                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "af-ja           Afrikaans from Japanese                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ak-ja           Akan from Japanese                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sq-ja           Albanian from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "am-ja           Amharic from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ar-ja           Arabic from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hy-ja           Armenian from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "as-ja           Assamese from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ay-ja           Aymara from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "az-ja           Azerbaijani from Japanese                        vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bn-ja           Bangla from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "eu-ja           Basque from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "be-ja           Belarusian from Japanese                         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bho-ja          Bhojpuri from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bs-ja           Bosnian from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "bg-ja           Bulgarian from Japanese                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "my-ja           Burmese from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ca-ja           Catalan from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ceb-ja          Cebuano from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zh-Hans-ja      Chinese (Simplified) from Japanese               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zh-Hant-ja      Chinese (Traditional) from Japanese              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "co-ja           Corsican from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hr-ja           Croatian from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "cs-ja           Czech from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "da-ja           Danish from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "dv-ja           Divehi from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "nl-ja           Dutch from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "en-ja           English from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "eo-ja           Esperanto from Japanese                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "et-ja           Estonian from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ee-ja           Ewe from Japanese                                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fil-ja          Filipino from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fi-ja           Finnish from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fr-ja           French from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gl-ja           Galician from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lg-ja           Ganda from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ka-ja           Georgian from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "de-ja           German from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "el-ja           Greek from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gn-ja           Guarani from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gu-ja           Gujarati from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ht-ja           Haitian Creole from Japanese                     vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ha-ja           Hausa from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "haw-ja          Hawaiian from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "iw-ja           Hebrew from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hi-ja           Hindi from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hmn-ja          Hmong from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "hu-ja           Hungarian from Japanese                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "is-ja           Icelandic from Japanese                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ig-ja           Igbo from Japanese                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "id-ja           Indonesian from Japanese                         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ga-ja           Irish from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "it-ja           Italian from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ja-ja           Japanese from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "jv-ja           Javanese from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kn-ja           Kannada from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kk-ja           Kazakh from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "km-ja           Khmer from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "rw-ja           Kinyarwanda from Japanese                        vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ko-ja           Korean from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "kri-ja          Krio from Japanese                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ku-ja           Kurdish from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ky-ja           Kyrgyz from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lo-ja           Lao from Japanese                                vtt, ttml, srv3, srv2, srv1, json3\n" +
            "la-ja           Latin from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lv-ja           Latvian from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ln-ja           Lingala from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lt-ja           Lithuanian from Japanese                         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "lb-ja           Luxembourgish from Japanese                      vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mk-ja           Macedonian from Japanese                         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mg-ja           Malagasy from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ms-ja           Malay from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ml-ja           Malayalam from Japanese                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mt-ja           Maltese from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mi-ja           MÄ\u0081ori from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mr-ja           Marathi from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "mn-ja           Mongolian from Japanese                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ne-ja           Nepali from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "nso-ja          Northern Sotho from Japanese                     vtt, ttml, srv3, srv2, srv1, json3\n" +
            "no-ja           Norwegian from Japanese                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ny-ja           Nyanja from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "or-ja           Odia from Japanese                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "om-ja           Oromo from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ps-ja           Pashto from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fa-ja           Persian from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pl-ja           Polish from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pt-ja           Portuguese from Japanese                         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "pa-ja           Punjabi from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "qu-ja           Quechua from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ro-ja           Romanian from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ru-ja           Russian from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sm-ja           Samoan from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sa-ja           Sanskrit from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "gd-ja           Scottish Gaelic from Japanese                    vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sr-ja           Serbian from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sn-ja           Shona from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sd-ja           Sindhi from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "si-ja           Sinhala from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sk-ja           Slovak from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sl-ja           Slovenian from Japanese                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "so-ja           Somali from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "st-ja           Southern Sotho from Japanese                     vtt, ttml, srv3, srv2, srv1, json3\n" +
            "es-ja           Spanish from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "su-ja           Sundanese from Japanese                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sw-ja           Swahili from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "sv-ja           Swedish from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tg-ja           Tajik from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ta-ja           Tamil from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tt-ja           Tatar from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "te-ja           Telugu from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "th-ja           Thai from Japanese                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ti-ja           Tigrinya from Japanese                           vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ts-ja           Tsonga from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tr-ja           Turkish from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "tk-ja           Turkmen from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "uk-ja           Ukrainian from Japanese                          vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ur-ja           Urdu from Japanese                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ug-ja           Uyghur from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "uz-ja           Uzbek from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "vi-ja           Vietnamese from Japanese                         vtt, ttml, srv3, srv2, srv1, json3\n" +
            "cy-ja           Welsh from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "fy-ja           Western Frisian from Japanese                    vtt, ttml, srv3, srv2, srv1, json3\n" +
            "xh-ja           Xhosa from Japanese                              vtt, ttml, srv3, srv2, srv1, json3\n" +
            "yi-ja           Yiddish from Japanese                            vtt, ttml, srv3, srv2, srv1, json3\n" +
            "yo-ja           Yoruba from Japanese                             vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zu-ja           Zulu from Japanese                               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "[info] Available subtitles for gqiRJn7me-s:\n" +
            "Language Name                  Formats\n" +
            "zh-Hans  Chinese (Simplified)  vtt, ttml, srv3, srv2, srv1, json3\n" +
            "zh-Hant  Chinese (Traditional) vtt, ttml, srv3, srv2, srv1, json3\n" +
            "en       English               vtt, ttml, srv3, srv2, srv1, json3\n" +
            "ja       Japanese              vtt, ttml, srv3, srv2, srv1, json3\n";

}
