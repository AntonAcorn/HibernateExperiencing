package org.example;


import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.transaction.UserTransaction;

public class App {
    public static void main(String[] args) {   //Явно здесь файл hibernate.properties не подключается, но под копотом подключается. поэтому файл обязан называться hibernate.properties
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class); //передаем класс, который помечен @Entity

        SessionFactory sessionFactory = configuration.buildSessionFactory();  //Создаем фабрику
        Session session = sessionFactory.getCurrentSession();    //Из фабрики создаем сессиию
        try {
            session.beginTransaction();  //Открываем транзакцию

            //*********Добавление********************
//            Person testPerson = new Person("Test Person", 50);
//            Passport passport = new Passport(1234);
//            testPerson.setPassport(passport);
//            //testPerson.setPassport(passport);//обеспечение двусторонней связи, можно рефактор сделать и упаковать в setPassport
//            session.save(testPerson);

            //*********Чтение********************
//            Person person = session.get(Person.class, 1);
//            System.out.println(person.getPassport().getPassportNumber());
//
//            Passport passport = session.get(Passport.class, 1);
//            System.out.println(passport.getPerson().getName());

            //*********Переназначение********************
//            Person person = session.get(Person.class, 1);
//            person.getPassport().setPassportNumber(1234556);   //состояние persistence, поэтому hibernate отслеживает все состояния


            //*********Удаление********************
            Person person = session.get(Person.class, 1);
            session.remove(person);//благодаря каскадированию удалится и пасспорт, и человек

            session.getTransaction().commit(); //выполняем транзацию


        } finally {
            sessionFactory.close(); //закрываем фабрику
        }
    }
}
