package cc.wordview.api.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "language_word")
public class LanguageWord implements Serializable {
        private static final long serialVersionUID = 2235567849326126355L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "localized_word")
        private String localizedWord;

        @Column(name = "lang")
        private String lang;

        @Column(name = "id_word")
        private Long idWord;
}
