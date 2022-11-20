package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {   //Явно здесь файл hibernate.properties не подключается, но под копотом подключается. поэтому файл обязан называться hibernate.properties
        Configuration configuration = new Configuration(); //передаем класс, который помечен @Entity

        SessionFactory sessionFactory = configuration.buildSessionFactory();  //Создаем фабрику
        Session session = sessionFactory.getCurrentSession();    //Из фабрики создаем сессиию
        try {
            session.beginTransaction();  //Открываем транзакцию



            session.getTransaction().commit(); //выполняем транзацию

        } finally {
            sessionFactory.close(); //закрываем фабрику
        }
    }
}
