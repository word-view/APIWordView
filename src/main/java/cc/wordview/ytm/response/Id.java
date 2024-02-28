package cc.wordview.ytm.response;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Id {
        private String kind;

        @SerializedName("videoId")
        private String videoId;
}
