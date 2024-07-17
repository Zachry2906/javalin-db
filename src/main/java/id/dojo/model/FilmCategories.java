package id.dojo.model;

import java.util.List;

import org.sql2o.Sql2o;

import com.google.gson.Gson;
import id.dojo.DBConnect;
import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;

public class FilmCategories {
    private Integer film_id;
    private Integer category_id;
    private java.sql.Timestamp last_update;
    private Integer inventory_id;
    private Integer store_id;
    private Film film;


    static Sql2o sql2o = DBConnect.getSql2o();
    static Gson gson = new Gson();

    public static Res<List<FilmCategories>> listFilmCategories(){
        Res<List<FilmCategories>> data = new DBUtils<FilmCategories>().list("SELECT fc.film_id, fc.category_id, fc.last_update FROM film_category fc JOIN film f ON fc.film_id = f.film_id JOIN inventory i ON f.film_id = i.film_id LIMIT 10", FilmCategories.class);
        return data;
    }

    public void setFilm(Film film){
        this.film = film;
    }

    public Integer getFilm_id(){
        return this.film_id;
    }

    public Film getFilm(){
        return this.film;
    }

    public Integer getCategory_id(){
        return this.category_id;
    }

    public void setInventory_id(Integer inventory_id){
        this.inventory_id = inventory_id;
    }

    public void setStore_id(Integer store_id){
        this.store_id = store_id;
    }



}
