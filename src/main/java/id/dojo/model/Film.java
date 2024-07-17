package id.dojo.model;

import java.util.List;

import id.dojo.helper.Res;
import id.dojo.helper.DBUtils;

public class Film {
    private Integer film_id;
    private String title;
    private String description;
    private Integer actor_in_film;
    List<Actor> actor;

    public static Res<List<Film>> listFilm(){
        Res<List<Film>> data = new DBUtils<Film>().list("SELECT film_id, title, description FROM film;", Film.class);
        return data;
    }

    public static Res<Film> getFilmById(Integer film_id){
        Res<Film> data = new DBUtils<Film>().get("SELECT film_id, title, description FROM film WHERE film_id = :p1;", film_id, Film.class);
        return data;
    }
    
    public static Res<List<Film>> listFilmJoinActor(){
        Res<List<Film>> data = new DBUtils<Film>().list("SELECT f.film_id, COUNT(a.actor_id) AS actor_in_film FROM film f JOIN film_actor fa ON f.film_id = fa.film_id JOIN actor a ON fa.actor_id = a.actor_id GROUP BY f.film_id LIMIT 10;", Film.class);
        return data;
    }

    public void setActor(List<Actor> actor){
        this.actor = actor;
    }

    public Integer getFilm_id(){
        return this.film_id;
    }

    public List <Actor> getActor(){
        return this.actor;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
