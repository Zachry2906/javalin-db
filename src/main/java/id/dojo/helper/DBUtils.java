package id.dojo.helper;

import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import id.dojo.DBConnect;
import java.util.List;

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

    public Res<T> insert(String query, Object params, Class<T> type){
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
}
