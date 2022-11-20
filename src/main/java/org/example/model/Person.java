package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //сущность для БД
@Table(name = "Person") //Название таблицы из БД. Среда помечает ошибку, но она не видит, базу. Все работает
public class Person {
    @Id
    @Column(name = "id")// пишем название столбца из самой таблицы, чтобы БД сопоставлялась с классом
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;

    public Person() {
    }

    public Person(int id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
