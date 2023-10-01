package cc.wordview.api.request.langword;

import cc.wordview.api.database.entity.LangWord;
import cc.wordview.api.exception.RequestValidationException;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.isNull;
import static cc.wordview.api.request.ExceptionTemplate.*;

@Getter
@Setter
public class CreateRequest {
        public String localizedWord;
        public String lang;
        public Long idWord;
        public String authorization;

        public LangWord toEntity() throws RequestValidationException {
                this.validate();

                LangWord langWord = new LangWord();

                langWord.setLocalizedWord(localizedWord);
                langWord.setLang(lang);
                langWord.setIdWord(idWord);

                return langWord;
        }

        private void validate() throws RequestValidationException {
                if (isNull(localizedWord) || localizedWord.isEmpty()) {
                        throw emptyOrNull("localizedWord");
                }

                if (isNull(lang) || lang.isEmpty()) {
                        throw emptyOrNull("lang");
                }

                if (isNull(idWord)) {
                        throw emptyOrNull("idWord");
                }

                if (isNull(authorization) || authorization.isEmpty()) {
                        throw emptyOrNull("authorization");
                }

        }
}
