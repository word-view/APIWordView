package cc.wordview.api.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "language_word")
public class LangWord implements Serializable {
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
