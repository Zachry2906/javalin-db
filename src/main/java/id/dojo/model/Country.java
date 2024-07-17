package id.dojo.model;

import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;


public class Country {
    Integer country_id;
    String country;
    java.sql.Timestamp last_update;

    public static Res<Country> getCountryById(Integer country_id){
        Res<Country> data = new DBUtils<Country>().get("SELECT country_id, country, last_update FROM country WHERE country_id = :p1",country_id, Country.class);
        return data;
    }

    public Integer getCountry_id(){
        return this.country_id;
    }

    public String getCountry(){
        return this.country;
    }

    public java.sql.Timestamp getLast_update(){
        return this.last_update;
    }

    public void setCountry_id(Integer country_id){
        this.country_id = country_id;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public void setLast_update(java.sql.Timestamp last_update){
        this.last_update = last_update;
    }

}
