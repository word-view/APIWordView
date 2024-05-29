package cc.wordview.api.service.specification;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.sapher.youtubedl.YoutubeDLException;

import cc.wordview.ytm.response.LyricEntry;
import cc.wordview.ytm.response.SearchResult;
import cc.wordview.ytm.response.Video;

public interface MusicServiceInterface {
        Video getHistory() throws IOException;

        List<LyricEntry> getSubtitlesList(String id) throws YoutubeDLException;

        String getSubtitle(String id, String lang) throws YoutubeDLException, IOException;

        Path download(String id) throws YoutubeDLException;

        List<SearchResult> search(String query, int maxResults) throws IOException;
}
