package id.dojo.controller;

import io.javalin.http.Handler;
import static id.dojo.model.Actor.*;
import static id.dojo.Main.gson;
import id.dojo.helper.Res;

public class ActorController {
    public static Handler listActor = ctx -> {
        ctx.json(gson.toJson(testConnect()));
    };

    public static Handler getActor = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ctx.json(gson.toJson(getActor(id)));
    };

    public static Handler updateActor = ctx -> {
        int id = Integer.parseInt(ctx.formParam("id"));
        String firstName = ctx.formParam("first_name");
        String lastName = ctx.formParam("last_name");
        if (id == 0 || firstName == null || lastName == null) {
            ctx.json(gson.toJson(new Res<>("Data tidak lengkap", null)));
        } else {
            ctx.json(gson.toJson(updateActor(id, firstName, lastName)));
        }
    };

    public static Handler insertActor = ctx -> {
        String firstName = ctx.formParam("first_name");
        String lastName = ctx.formParam("last_name");
        if (firstName == null || lastName == null) {
            ctx.json(gson.toJson(new Res<>("Data tidak lengkap", null)));
        } else {
            ctx.json(gson.toJson(insertActor(firstName, lastName)));
        }
    };

    public static Handler deleteActor = ctx -> {
        int id = Integer.parseInt(ctx.formParam("id"));
        ctx.json(gson.toJson(deleteActorr(id)));
    };


}
