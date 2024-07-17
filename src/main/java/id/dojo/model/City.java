package id.dojo.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.sql2o.Sql2o;

import id.dojo.helper.Res;
import id.dojo.helper.DBUtils;
import com.google.gson.Gson;
import id.dojo.DBConnect;

public class City {
    private int city_id;
    private String city;
    private int country_id;
    private Timestamp last_update;
    private Country country;


    static Gson gson = new Gson();
    static Sql2o sql2o = DBConnect.getSql2o();

    public City(int city_id, String city, int country_id) {
        this.city_id = city_id;
        this.city = city;
        this.country_id = country_id;
        this.last_update = new Timestamp(System.currentTimeMillis());
    }

    public City(String city, int country_id) {
        this.city = city;
        this.country_id = country_id;
        this.last_update = new Timestamp(System.currentTimeMillis());
    }

    public City() {
    }

    public static Res<List<City>> getList(Map<String, String> params, Integer page){
        String sql = "SELECT ci.city_id, ci.city, ci.country_id, ci.last_update FROM city ci JOIN country co ON ci.country_id = co.country_id WHERE TRUE ";

        String where = "";

        if (params.get("city") != null && params.get("city") != "") {
            where += "AND city ILIKE '%" + params.get("city") + "%'";
        }

        String limit = " LIMIT 10 OFFSET " + (page - 1) * 10;

        Res data = new DBUtils().list(sql + where + " ORDER BY city_id" + limit, City.class);
        return data;
    }

    public static Res<City> getCityById(Integer id){
        Res<City> data = new DBUtils<City>().get("UPDATE city SET city = :city, country_id = :country_id WHERE city_id",id, City.class);
        return data;
    }

    public Integer getCity_id(){
        return this.city_id;
    }

    public void setCountry(Country country){
        this.country = country;
    }

    public Country getCountry(){
        return this.country;
    }

    public String getCity(){
        return this.city;
    }

    public int getCountry_id(){
        return this.country_id;
    }

    public Timestamp getLast_update(){
        return this.last_update;
    }

    public void setCity_id(int city_id){
        this.city_id = city_id;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setCountry_id(int country_id){
        this.country_id = country_id;
    }

    public void setLast_update(Timestamp last_update){
        this.last_update = last_update;
    }

    public static Res<City> updateCity(City city) {
        String sql = "UPDATE city SET city = :city, country_id = :country_id WHERE city_id = :city_id";
        System.out.println(city);
        return new DBUtils<City>().updateBind(sql, city);
    }

    public static Res<City> insertCity(City city) {
        String sql = "INSERT INTO city (city, country_id, last_update) VALUES (:city, :country_id, :last_update)";
        return new DBUtils<City>().insertBind(sql, city);
    }

}
