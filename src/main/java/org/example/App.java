package org.example;


import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {   //Явно здесь файл hibernate.properties не подключается, но под копотом подключается. поэтому файл обязан называться hibernate.properties
        Configuration configuration = new Configuration().
                addAnnotatedClass(Person.class).
                addAnnotatedClass(Item.class); //передаем класс, который помечен @Entity

        SessionFactory sessionFactory = configuration.buildSessionFactory();  //Создаем фабрику
        Session session = sessionFactory.getCurrentSession();    //Из фабрики создаем сессиию
        try {
            session.beginTransaction();  //Открываем транзакцию

            Person person = new Person("Test Cascading", 30);
            Item item = new Item("Test Cascading item");
            person.setItems(new ArrayList<>(Collections.singletonList(item)));

            //session.save(person);//Сохранение без каскадирования
            session.persist(person);// вызов с каскадированием
            //session.persist(item) // это делает уже hibernate блягодаря каскадированию

            session.getTransaction().commit(); //выполняем транзацию

        } finally {
            sessionFactory.close(); //закрываем фабрику
        }
    }
}
