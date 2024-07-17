package id.dojo.helper;

import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import id.dojo.DBConnect;
import java.util.List;
import java.lang.reflect.Field;

public class DBUtils<T>{
    static private Sql2o Sql2o = DBConnect.getSql2o();

    public Res<List<T>> list(String query, Class<T> type){
        try (org.sql2o.Connection con = Sql2o.open()) {
            List<T> data = con.createQuery(query).executeAndFetch(type);
            return new Res<List<T>>("Berhasil ambil data film", data);
        } catch (Sql2oException e) {
            e.printStackTrace();
            return new Res<List<T>>(e.toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Res(e.toString(), null);
        }
    }

    public Res<List<T>> listActor(String query, Integer id, Class<T> type){
        try (org.sql2o.Connection con = Sql2o.open()) {
            List<T> data = con.createQuery(query).withParams(id).executeAndFetch(type);
            return new Res<List<T>>("Berhasil ambil data film", data);
        } catch (Sql2oException e) {
            e.printStackTrace();
            return new Res<List<T>>(e.toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Res(e.toString(), null);
        }
    }


    public  Res<T> get(String query, Object params, Class<T> type){
        try (org.sql2o.Connection con = Sql2o.open()) {
            T data = con.createQuery(query).withParams(params).executeAndFetchFirst(type);
            return new Res<T>("success", data);
        } catch (Sql2oException e) {
            e.printStackTrace();
            return new Res<T>(e.toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Res(e.toString(), null);
        }
    }

    public Res<T> insert(String query, T mapper){
        try (org.sql2o.Connection con = Sql2o.open()) {
            Object post = con.createQuery(query).bind(mapper).executeUpdate();
            return new Res("success", " ");
        } catch (Sql2oException e) {
            e.printStackTrace();
            return new Res<T>(e.toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Res(e.toString(), null);
        }
    }

    public Res<T> update(String query, Object params, Class<T> type){
        try (org.sql2o.Connection con = Sql2o.open()) {
            T data = con.createQuery(query).withParams(params).executeAndFetchFirst(type);
            return new Res<T>("success", data);
        } catch (Sql2oException e) {
            e.printStackTrace();
            return new Res<T>(e.toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Res(e.toString(), null);
        }
    }

    public Res<T> delete(String query, Object params, Class<T> type){
        try (org.sql2o.Connection con = Sql2o.open()) {
            T data = con.createQuery(query).withParams(params).executeAndFetchFirst(type);
            return new Res<T>("success", data);
        } catch (Sql2oException e) {
            e.printStackTrace();
            return new Res<T>(e.toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Res(e.toString(), null);
        }
    }

    public Res<T> updateBind(String query, T mapper) {
        try (org.sql2o.Connection con = Sql2o.beginTransaction()) {
            try {

                int updatedCount = con.createQuery(query)
                                     .bind(mapper)
                                     .executeUpdate()
                                     .getResult();
                
                if (updatedCount > 0) {
                    con.commit();
                    return new Res<>("Success: " + updatedCount + " row(s) updated", null);
                } else {
                    con.rollback();
                    return new Res<>("No rows updated", null);
                }
            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        } catch (Sql2oException e) {
            e.printStackTrace();
            return new Res<>("Database error: " + e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Res<>("Unexpected error: " + e.getMessage(), null);
        }
    }

    public Res<T> insertBind(String query, T mapper) {
        try (org.sql2o.Connection con = Sql2o.beginTransaction()) {
            try {

                int insertedCount = con.createQuery(query)
                                     .bind(mapper)
                                     .executeUpdate()
                                     .getResult();
                
                if (insertedCount > 0) {
                    con.commit();
                    return new Res<>("Success: " + insertedCount + " row(s) inserted", null);
                } else {
                    con.rollback();
                    return new Res<>("No rows inserted", null);
                }
            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        } catch (Sql2oException e) {
            e.printStackTrace();
            return new Res<>("Database error: " + e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Res<>("Unexpected error: " + e.getMessage(), null);
        }
    }
}
