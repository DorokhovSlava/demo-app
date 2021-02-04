package org.dorokhov.demoapp.entityes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notes")
public class Note implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "note_id")
    private int note_id;

    @Column(name = "title")
    private String title;

    @Column(name = "note_text")
    private String noteText;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "date_notice")
    private LocalDate dateNotice;

    @Column(name = "user_id")
    private int user_id;

    @JsonBackReference
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = LocalDate.now();
    }
}
