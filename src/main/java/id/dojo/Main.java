package id.dojo;

import com.google.gson.Gson;
import io.javalin.Javalin;
import id.dojo.controller.*;

import static id.dojo.model.Payment.fetchPayment;


public class Main {
    public static Gson gson = new Gson();

        public static void main(String[] args) {
            var app = Javalin.create(/*config*/)
                    //ActorController.listaActor harus secara eksplisit dideklarasikan sebagai handler
                    .get("/actors", ActorController.listActor)
                    .get("/actor/<id>", ActorController.getActor)
                    .get("/film-actor", FilmController.listFilmJoinActor)
                    .put("/actor/update", ActorController.updateActor)
                    .post("/actor", ActorController.insertActor)
                    .delete("/actor/delete", ActorController.deleteActor)
                    //javalin secara otomatis mengenali lambda expression sebagai handler yang akan dijalankan
                    .get("/payment", ctx -> {
                        ctx.json(gson.toJson(fetchPayment()));
                    })
                    .get("/film-categories", FilmCategoriesController.listFilmCategories)
                    .start(7070);
        }
}