package cc.wordview.api.response.lesson;

import cc.wordview.api.database.entity.Word;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordWithoutId {
        public String name;
        public String imageURL;

        public WordWithoutId(Word word) {
                setName(word.getName());
                setImageURL("https://localhost:8443/img/" + name + ".png");
        }
}
