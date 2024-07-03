package id.dojo;

import com.google.gson.Gson;
import io.javalin.Javalin;
import id.dojo.controller.*;

import static id.dojo.model.Payment.fetchPayment;


public class Main {
    public static Gson gson = new Gson();

        public static void main(String[] args) {
            var app = Javalin.create(/*config*/)
                    .get("/", ActorController.listActor)
                    .get("/actor/<id>", ActorController.getActor)
                    .put("/actor/update", ActorController.updateActor)
                    .post("/actor", ActorController.insertActor)
                    .delete("/actor/delete", ActorController.deleteActor)
                    
                    .get("/payment", ctx -> {
                        ctx.json(gson.toJson(fetchPayment()));
                    })
                    .start(7070);
        }
}