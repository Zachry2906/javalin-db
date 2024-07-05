package id.dojo.model;

import id.dojo.DBConnect;
import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.Timestamp;
import java.util.List;
import com.google.gson.Gson;

import static id.dojo.Main.gson;


public class Actor {
    private int actor_id;
    private String first_name;
    private String last_name;
    private Timestamp last_update;
    static Sql2o sql2o = DBConnect.getSql2o();
    static Connection con = DBConnect.wrapConn();


    @Override
    public String toString() {
        return "id " + this.getActor_id() + " " + this.getFirst_name() + " " + this.getLast_name() + " " + last_update + "\n";
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    // public static String listActor(){
    //     Res data = new DBUtils().list("SELECT actor_id, first_name, last_name, last_update FROM actor;", Actor.class);
    //     return gson.toJson(data);
    // }

    public static Res<List<Actor>> testConnect(){
        try(Connection con = sql2o.open()){
            System.out.println("Connected to database");
            List<Actor> test = con.createQuery("SELECT actor_id, first_name, last_name, last_update FROM actor").executeAndFetch(Actor.class);
            Res<List<Actor>> res = new Res<List<Actor>>("Berhasil fetch", test);
            return res;
//        } catch(Sql2oException ex) {
//            return ex.toString();
        }
    }

    public static Res<Integer> deleteActor(int actor_id) {
        try (Connection con = sql2o.open()) {
            System.out.println("Connected to database");
            System.out.println(actor_id);

            try {
                con.createQuery("DELETE FROM film_actor WHERE actor_id = :actor_id")
                        .addParameter("actor_id", actor_id)
                        //mengasign actor_id ke parameter :actor_id
                        .executeUpdate();

                int delete = con.createQuery("DELETE FROM actor WHERE actor_id = :actor_id")
                        .addParameter("actor_id", actor_id)
                        .executeUpdate().getResult();

                Res<Integer> res = new Res<>("Berhasil delete", delete);
                return res;
            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
            return new Res<>("Gagal delete: " + ex.getMessage(), null);
        }
    }

    public static Res<Integer> updateActor(int actor_id, String first_name, String last_name) {
        try (Connection con = sql2o.open()) {
            System.out.println("Connected to database");
            String sql = "UPDATE actor SET first_name = :firstName, last_name = :lastName WHERE actor_id = :actorId";
            int update = con.createQuery(sql)
                .addParameter("firstName", first_name)
                .addParameter("lastName", last_name)
                .addParameter("actorId", actor_id)
                .executeUpdate()
                .getResult();
    
            if (update > 0) {
                return new Res<>("Berhasil update", update);
            } else {
                return new Res<>("Tidak ada baris yang diupdate", 0);
            }
        } catch (Sql2oException ex) {
            return new Res<>("Gagal update: " + ex.getMessage(), null);
        }
    }

    public static Res<List<Actor>> getActor(int actor_id){
        try(Connection con = sql2o.open()){
            System.out.println("Connected to database");
            List<Actor> test = con.createQuery("SELECT actor_id, first_name, last_name, last_update FROM actor WHERE actor_id = " + actor_id).executeAndFetch(Actor.class);
            Res<List<Actor>> res = new Res<List<Actor>>("Berhasil fetch", test);
            return res;
       } catch(Sql2oException ex) {
           return new Res<List<Actor>>("Gagal : " + ex.getMessage(), null);
        }
    }

    public static Res<Integer> insertActor(String first_name, String last_name){
        try(Connection con = sql2o.open()){
            System.out.println("Connected to database");
            String sql = "INSERT INTO actor (first_name, last_name) VALUES (:firstName, :lastName)";
            int ins = con.createQuery(sql)
                .addParameter("firstName", first_name)
                .addParameter("lastName", last_name)
                .executeUpdate()
                .getResult();
    
            if (ins > 0) {
                return new Res<>("Berhasil insert", ins);
            } else {
                return new Res<>("Tidak ada baris yang diinsert", 0);
            }
        } catch (Sql2oException ex) {
            return new Res<>("Gagal insert: " + ex.getMessage(), null);
        }
    }

    public static Res<Integer> deleteActorr(int actor_id) {
        try (Connection con = sql2o.open()) {
            System.out.println("Connected to database");
            System.out.println(actor_id);

            try {
                // Hapus dari film_actor
                con.createQuery("DELETE FROM film_actor WHERE actor_id = :actor_id")
                        .addParameter("actor_id", actor_id)
                        .executeUpdate();

                // Hapus dari actor dan dapatkan jumlah baris yang dihapus
                int deletedRows = con.createQuery("DELETE FROM actor WHERE actor_id = :actor_id")
                        .addParameter("actor_id", actor_id)
                        .executeUpdate().getResult();
                
                if (deletedRows > 0) {
                    return new Res<>("Berhasil delete", deletedRows);
                } else {
                    return new Res<>("Tidak ada baris yang dihapus", 0);
                }
            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
            return new Res<>("Gagal delete: " + ex.getMessage(), null);
        }
    }
}
