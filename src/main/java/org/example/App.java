package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
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

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            //************По Person***********
            Person person = session.get(Person.class,1);//здесь hibernate не получает товары, т.е. запрос
                                                                    // только на человека
            System.out.println("Мы получили человека");

            //получим связанные сущности(Lazy)
            //person.getItems(); //при таком вызове может возникнуть ошибка, потому что компилятор проигнорирует геттер,
            //который не дает никаких результатов. Решение - обернуть в Hibernate.initialize();
            Hibernate.initialize(person.getItems());
            System.out.println(person.getItems());  //только здесь делается второй запрос уже на таблицу товаров

            //************По Item***********
//            Item item = session.get(Item.class,1);
//            System.out.println("Мы получили товар");
//
//            System.out.println(item.getOwner());//жадный тип, выполняется один sql запрос в 32 строчке, потому что ManyTo One

            session.getTransaction().commit();
            // session.close() автоматически после commit
            //после жадного поглощения данный уже подгружены, соответственно,
            // поле закрытия сессии можно использовать эти данные

            //************ Как достать еще данные уже после закрытия сессии?***********
            //открываем и начинаем сессию еще раз
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Внутри второй транзацкии");
            //Эта сессия ничего не знает о person из первой сессии, нам нужно тот объект привязать к этой сессии

            person = (Person) session.merge(person);//то, что возвразает метод merge мы должны положить в переменную person
//            List<Items> items = session.createQuery("select i from Item i where i.owner.id =:personId", Item.class). при помощи HQL запроса
//                    setParameter("PersonId", person.getId()).getResultList();

            Hibernate.initialize(person.getItems());
            session.getTransaction().commit();
            System.out.println("Вне сессии");
        } finally {
            sessionFactory.close(); //закрываем фабрику
        }
    }
}
