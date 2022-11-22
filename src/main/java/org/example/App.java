package org.example;


import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {   //Явно здесь файл hibernate.properties не подключается, но под копотом подключается. поэтому файл обязан называться hibernate.properties
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class); //передаем класс, который помечен @Entity

        SessionFactory sessionFactory = configuration.buildSessionFactory();  //Создаем фабрику

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();    //Из фабрики создаем сессиию
            session.beginTransaction();  //Открываем транзакцию

            //*********Создание**********
//            Movie movie = new Movie("Pulp Fiction", 1994);
//            Actor actor1 = new Actor("Harvey Keitel", 81);
//            Actor actor2 = new Actor("Samuel L.Jackson", 72);
//
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2))); //такой список неизменяемый,
                                                                        // а new ArrayList<>(Arrays.asList()) изменяемый,
            //обратная связь                                            // но нерасширяемый
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));//лист из одного элемента
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

            //*********Получение**********
//            Movie movie = session.get(Movie.class, 1);
//            System.out.println(movie.getActors());
//            Actor actor = session.get(Actor.class,1);
//            System.out.println(actor.getMovies());

            //*********Изменение**********
//            Movie movie = new Movie("Reservoir Dogs", 1992);
//            Actor actor = session.get(Actor.class, 1);
//            movie.setActors(new ArrayList<>(Collections.singletonList(actor)));//nosql
//            actor.getMovies().add(movie); //hibernate
//
//            session.save(movie);

            //*********Удаление**********
            Actor actor = session.get(Actor.class,2);
            System.out.println(actor.getMovies());

            Movie movieToRemove = actor.getMovies().get(0);//фильм с индексом ноль из листа

            actor.getMovies().remove(0);
            movieToRemove.getActors().remove(actor);


            session.getTransaction().commit(); //выполняем транзацию
        }
    }
}
