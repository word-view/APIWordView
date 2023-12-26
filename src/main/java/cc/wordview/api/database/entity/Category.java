package cc.wordview.api.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "lesson")
public class Category implements Serializable {
        private static final long serialVersionUID = 2555915241326436355L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "title")
        private String title;

}
