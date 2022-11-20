package org.example;


import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {   //Явно здесь файл hibernate.properties не подключается, но под копотом подключается. поэтому файл обязан называться hibernate.properties
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class); //передаем класс, который помечен @Entity

        SessionFactory sessionFactory = configuration.buildSessionFactory();  //Создаем фабрику
        Session session = sessionFactory.getCurrentSession();    //Из фабрики создаем сессиию
        try {
            session.beginTransaction();  //Открываем транзакцию
                //****** GET ******
////            Person person = session.get(Person.class, 1);  //1. Указываем какую именно сущность хотим получить (т.е. через Person.class он обращается к нужной таблице)
////            System.out.println(person.getName());
////            System.out.println(person.getAge());
//
//            ***** SAVE *******
//            Person person = new Person("Bob", 13);
//            Person person2 = new Person("Tom", 23);
//            Person person3 = new Person("Mike", 3);
//
//            session.save(person);
//            session.save(person2);
//            session.save(person3);

            // ********Update*******
//            Person person = session.get(Person.class, 2);
//            person.setName("Artem");

            //********Delete*******
//            Person person = session.get(Person.class, 2);
//            session.delete(person);

            //*******getId********
            Person person = new Person("Bender", 145);
            session.save(person);

            session.getTransaction().commit(); //выполняем транзацию

            System.out.println(person.getId()); //уже после коммита у того объекта хранится id, hib делает это автоматом
        } finally {
            sessionFactory.close(); //закрываем фабрику
        }
    }
}
