package id.dojo.model;

import java.util.List;

import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;

public class Inventory {
    private Integer inventory_id;
    private Integer store_id;

        public static Res<List<Inventory>> listFilm(){
        Res<List<Inventory>> data = new DBUtils<Inventory>().list("SELECT inventory_id, store_id FROM inventory;", Inventory.class);
        return data;
    }

    public static Res<Integer> getFilmById(Integer film_id){
        Res<Integer> data = new DBUtils<Integer>().get("SELECT inventory_id FROM inventory WHERE film_id = :p1;", film_id, Integer.class);
        return data;
    }

    public static Res<Integer> getStoreById(Integer film_id){
        Res<Integer> data = new DBUtils<Integer>().get("SELECT store_id FROM inventory WHERE film_id = :p1;", film_id, Integer.class);
        return data;
    }

    public Inventory(Integer inventory_id, Integer store_id){
        this.inventory_id = inventory_id;
        this.store_id = store_id;
    }

    public Integer getInventory_id(){
        return this.inventory_id;
    }

    public Integer getStore_id(){
        return this.store_id;
    }
}
