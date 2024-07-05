package id.dojo.model;

import java.util.List;

import id.dojo.helper.Res;
import id.dojo.helper.DBUtils;

public class Film {
    private Integer film_id;
    private String title;
    private String description;

    public static Res<List<Film>> listFilm(){
        Res<List<Film>> data = new DBUtils<Film>().list("SELECT film_id, title, description FROM film;", Film.class);
        return data;
    }

    public static Res<Film> getFilmById(Integer film_id){
        Res<Film> data = new DBUtils<Film>().get("SELECT film_id, title, description FROM film WHERE film_id = :p1;", film_id, Film.class);
        return data;
    }
    
}
