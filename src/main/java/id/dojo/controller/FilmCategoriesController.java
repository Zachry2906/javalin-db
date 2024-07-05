package id.dojo.controller;

import io.javalin.http.Handler;

import java.util.List;

import com.google.gson.Gson;

import id.dojo.helper.Res;
import id.dojo.model.FilmCategories;
import id.dojo.model.Film;
import id.dojo.model.Inventory;

public class FilmCategoriesController {
    static Gson gson = new Gson();

    public static Handler listFilmCategories = ctx -> {
        Res<List<FilmCategories>> data = FilmCategories.listFilmCategories();
        List<FilmCategories> filmCategories = data.getData();
        for (FilmCategories filmCategory : filmCategories) {
            Film film = Film.getFilmById(filmCategory.getFilm_id()).getData();
            Inventory inventory = Inventory.getFilmById(filmCategory.getFilm_id()).getData();
            filmCategory.setFilm(film);
            filmCategory.setInventory(inventory);
        }
        ctx.json(gson.toJson(data));
    };
}
