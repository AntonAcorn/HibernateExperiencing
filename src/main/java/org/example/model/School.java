package org.example.model;

import javax.persistence.*;
@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column(name = "name_of_school")
    private String nameOfSchool;

    @OneToOne
    @JoinColumn(name = "id_director", referencedColumnName = "id")
    private Director director;

    public School() {
    }

    public School(String nameOfSchool) {
        this.nameOfSchool = nameOfSchool;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfSchool() {
        return nameOfSchool;
    }

    public void setNameOfSchool(String nameOfSchool) {
        this.nameOfSchool = nameOfSchool;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", nameOfSchool='" + nameOfSchool + '\'' +
                ", director=" + director +
                '}';
    }
}
