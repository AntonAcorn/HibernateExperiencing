package org.example.model;

import javax.persistence.*;

@Entity //сущность для БД
@Table(name = "Person") //Название таблицы из БД. Среда помечает ошибку, но она не видит, базу. Все работает
public class Person {
    @Id
    @Column(name = "id")// пишем название столбца из самой таблицы, чтобы БД сопоставлялась с классом
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Указываем, что постгрес добавление id берет на себя
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
