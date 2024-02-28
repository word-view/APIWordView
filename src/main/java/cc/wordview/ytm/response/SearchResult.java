package cc.wordview.ytm.response;

import com.google.gson.annotations.SerializedName;

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

@Getter
@Setter
class Id {
    private String kind;

    @SerializedName("videoId")
    private String videoId;
}
