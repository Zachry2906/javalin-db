package id.dojo.controller;

import com.google.gson.reflect.TypeToken;
import io.javalin.http.Handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import id.dojo.helper.Res;
import id.dojo.model.Actor;
import id.dojo.model.City;
import id.dojo.model.Country;


public class CityController {
    static Gson gson = new Gson();

    public static Handler getList = ctx -> {
                Map<String, String> paramList = new HashMap<>();

        String page = ctx.pathParam("page");
        String city = ctx.queryParam("city");

        paramList.put("city", city);

        Res<List<City>> data = City.getList(paramList, Integer.valueOf(page));
        System.out.println(data);
        List<City> cities = data.getData();
        for (City city2 : cities) {
            Country country = Country.getCountryById(city2.getCity_id()).getData();
            city2.setCountry(country);
        }
        ctx.json(gson.toJson(data));
    };

    public static Handler getCityById = ctx -> {
        Integer id = Integer.valueOf(ctx.pathParam("id"));
        Res<City> data = City.getCityById(id);
        City city = data.getData();
        Country country = Country.getCountryById(city.getCity_id()).getData();
        city.setCountry(country);
        ctx.json(gson.toJson(data));
    };

    public static Handler updateCity = ctx -> {
        Map<String, Object> payload = gson.fromJson(ctx.body(), new TypeToken<Map<String, Object>>() {}.getType());

        Object city_id = payload.get("city_id");
        Object city = payload.get("city");
        Object country_id = payload.get("country_id");
        //hrhrhr
        int cityId = parseInteger(city_id);
        int countryId = parseInteger(country_id);
        System.out.println(cityId);
        System.out.println(city.toString());

        City city1 = new City(cityId, city.toString(), countryId);

        try {
            ctx.json(City.updateCity(city1));
        } catch (Exception e) {
            ctx.json(gson.toJson(new Res<>("Data tidak lengkap", null)));
            return;
        }
    };

    public static Handler insertCity = ctx -> {
        Map<String, Object> payload = gson.fromJson(ctx.body(), new TypeToken<Map<String, Object>>() {}.getType());

        Object city = payload.get("city");
        Object country_id = payload.get("country_id");

        int countryId = parseInteger(country_id);

        City city1 = new City( city.toString(), countryId);

        try {
            ctx.json(City.insertCity(city1));
        } catch (Exception e) {
            ctx.json(gson.toJson(new Res<>("Data tidak lengkap", null)));
            return;
        }
    };

    private static int parseInteger(Object value) {
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            try {
                return (int) Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format: " + value);
            }
        }
        throw new IllegalArgumentException("Cannot convert to integer: " + value);
    }
    
}
