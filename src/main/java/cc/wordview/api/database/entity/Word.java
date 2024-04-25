package cc.wordview.api.database.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "word")
public class Word implements Serializable {
	private static final long serialVersionUID = 4235915241326436355L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "id_lesson")
	private Long idLesson;

	@Column(name = "name")
	private String name;

	@Column(name = "lang")
	private String lang;

	@Column(name = "localized_word")
	private String localizedWord;

	@Column(name = "romanized_word")
	private String romanizedWord;
}
