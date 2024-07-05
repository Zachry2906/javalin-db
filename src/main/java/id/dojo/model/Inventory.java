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

    public static Res<Inventory> getFilmById(Integer film_id){
        Res<Inventory> data = new DBUtils<Inventory>().get("SELECT inventory_id, store_id FROM inventory WHERE inventory_id = :p1;", film_id, Inventory.class);
        return data;
    }
}
