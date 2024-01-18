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
@Table(name = "word")
public class Word implements Serializable {
	private static final long serialVersionUID = 4235915241326436355L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name_id")
	private String nameId;

	@Column(name = "id_lesson")
	private Long idLesson;
}
