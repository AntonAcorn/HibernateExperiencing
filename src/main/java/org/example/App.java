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
            //********Получаем person со всеми его item*******
//            Person person = session.get(Person.class, 3);
//            System.out.println(person);
//            List<Item> items = person.getItems(); //вызывать геттеры тольео внутри транзакции, иначе hibernate не будет знать
//            System.out.println(items);

            //********Получаем item по Person*******
//            Item item = session.get(Item.class, 5);
//            System.out.println(item);
//
//            Person person = item.getOwner();
//            System.out.println(person);

            ////********Кладем new item по выданному из базы человеку*******
//            Person person = session.get(Person.class, 2);
//            Item newItem = new Item("Item from Hibernate", person);
//            //эта строчка не делает sql запрос в базу, мы просто обновляем данные для hibernate
//            person.getItems().add(newItem); // hibernate кэширует, если не прописать обратную связь, то в бд все ок,
//                                            //но Hibernate вернет страго человека, без нового элемента.
//                                            //потому что он не будет делать новый запрос в базу, потому что хранит его в кэше
//            session.save(newItem);

            ////********Создаем нового человека и новый заказ*******
//            Person person = new Person("Klark", 28);
//            Item newItem = new Item("Item from Hibernate 2", person);//связь со стороны человека
//            //Collections.singletonList(newItem) создает список из одного товара(он незменяемый),
//            //но если мы его кладем в new ArrayList, то он становится изменяемым
//            person.setItems(new ArrayList<>(Collections.singletonList(newItem)));//связь со стороны человека
//
//            session.save(person);
//            session.save(newItem);

            ////********Проблема с каскадированием*******удаляем товар и человека
//            Person person = session.get(Person.class, 3);//получаем человека
//            List<Item> items = person.getItems();//получаем его товары
//            for (Item item : items)         //удалили все товары на стороне товаров (делает hibernate) SQL
//                session.remove(item);
//            person.getItems().clear();      //удалили товары на стороне человека(с БД это несвязанно) нет SQL
//                                            //необходимо, чтобы в кэше все было верно


//            Person person = session.get(Person.class, 2); //удаляем человека
//            //sql удаляет человека и делает каскадирование
//            session.remove(person);
//            //для правильного состояния hibernate кэша
//            person.getItems().forEach(items -> items.setOwner(null));

            ////********Меняем владельца существующего товара*******
            Person person = session.get(Person.class, 1);
            Item item = session.get(Item.class,2);
            item.getOwner().getItems().remove(item);//NOSQL

            item.setOwner(person);//SQL
            person.getItems().add(item);//NOSQL


            session.getTransaction().commit(); //выполняем транзацию

        } finally {
            sessionFactory.close(); //закрываем фабрику
        }
    }
}
