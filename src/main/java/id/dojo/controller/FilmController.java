package id.dojo.controller;
import io.javalin.http.Handler;
import java.util.List;
import com.google.gson.Gson;
import id.dojo.helper.Res;
import id.dojo.model.Film;
import id.dojo.model.FilmCategories;
import id.dojo.model.Inventory;
import id.dojo.model.Actor;

public class FilmController {

    static Gson gson = new Gson();

    public static Handler listFilmJoinActor = ctx -> {
        Res<List<Film>> data = Film.listFilmJoinActor();
        List<Film> films = data.getData();

            for (Film film : films) {
                String title = Film.getFilmById(film.getFilm_id()).getData().getTitle();
                String description = Film.getFilmById(film.getFilm_id()).getData().getDescription();
                film.setTitle(title);
                film.setDescription(description);
                Res<List<Actor>> actorsRes = Actor.getActorById(film.getFilm_id());
                List<Actor> actors = actorsRes.getData();
                    film.setActor(actors);
            }
        
        ctx.json(gson.toJson(data));
    };
    
}
