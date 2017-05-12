package ch.sebooom.openshift.sparktest.api;

import ch.sebooom.openshift.sparktest.JsonTransformer;
import ch.sebooom.openshift.sparktest.Response;
import ch.sebooom.openshift.sparktest.services.ElementService;

import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by sce on 12.05.2017.
 */
public class ElementResource {

    private static final String API_CONTEXT = "/api/v1";

    private final ElementService elementService;

    public ElementResource(ElementService elementService){
        this.elementService = elementService;
        setUpEndPoints();
    }

    private void setUpEndPoints() {



        post(API_CONTEXT + "/elements", "application/json", (request, response) -> {
            request.body();
            elementService.createNewElement(request.body());

           // response.

           // response.status(201);
            //return response;

            return new Response("201");
        }, new JsonTransformer());

       /* get(API_CONTEXT + "/todos/:id", "application/json", (request, response)

                -> todoService.find(request.params(":id")), new JsonTransformer());
*/
        get(API_CONTEXT + "/elements", "application/json", (request, response) -> {
            return elementService.findAll();
                },new JsonTransformer());
/*
        put(API_CONTEXT + "/todos/:id", "application/json", (request, response)

                -> todoService.update(request.params(":id"), request.body()), new JsonTransformer());*/
    }
}
