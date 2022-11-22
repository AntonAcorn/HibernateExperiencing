package org.example;


import org.example.model.Director;
import org.example.model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {   //Явно здесь файл hibernate.properties не подключается, но под копотом подключается. поэтому файл обязан называться hibernate.properties
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class)
                .addAnnotatedClass(School.class); //передаем класс, который помечен @Entity

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

//            Director director = new Director("Tom");
//            School school = new School("Usual Wonder");
//            director.setSchool(school);
//            session.save(director);


            //*********Чтение********************
//            Person person = session.get(Person.class, 1);
//            System.out.println(person.getPassport().getPassportNumber());
//
//            Passport passport = session.get(Passport.class, 1);
//            System.out.println(passport.getPerson().getName());
//            Director director = session.get(Director.class,2);
//            System.out.println(director.getSchool().getNameOfSchool());

//            School school = session.get(School.class,1);
//            System.out.println(school.getDirector().getSchool());

            //*********Переназначение********************
//            Person person = session.get(Person.class, 1);
//            person.getPassport().setPassportNumber(1234556);   //состояние persistence, поэтому hibernate отслеживает все состояния

            Director director = session.get(Director.class,2);
            director.getSchool().setNameOfSchool("Not Usual Wonder");
            School school = new School("new School");
            director.setSchool(school);


            //*********Удаление********************
//            Person person = session.get(Person.class, 1);
//            session.remove(person);//благодаря каскадированию удалится и пасспорт, и человек

            session.getTransaction().commit(); //выполняем транзацию


        } finally {
            sessionFactory.close(); //закрываем фабрику
        }
    }
}
