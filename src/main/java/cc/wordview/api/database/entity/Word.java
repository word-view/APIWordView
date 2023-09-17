package cc.wordview.api.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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
