package cc.wordview.ytm.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResult {
    private Snippet snippet;
    private String kind;
    private String etag;
    private Id id;
}
