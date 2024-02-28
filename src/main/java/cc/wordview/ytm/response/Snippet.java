package cc.wordview.ytm.response;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Snippet {
    @SerializedName("publishTime")
    private String publishTime;

    @SerializedName("publishedAt")
    private String publishedAt;

    private String description;
    private String title;
    private Thumbnails thumbnails;
    private String channelId;
    private String channelTitle;

    @SerializedName("liveBroadcastContent")
    private String liveBroadcastContent;
}
