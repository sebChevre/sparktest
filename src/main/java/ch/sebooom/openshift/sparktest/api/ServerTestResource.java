package ch.sebooom.openshift.sparktest.api;

import static spark.Spark.get;

/**
 * Created by sce on 12.05.2017.
 */
public class ServerTestResource {

    public ServerTestResource(){
        get("/", (req, res) -> "hello /");

        get("/hello", (req, res) -> "Hello /hello");

        get("/hello2", (req, res) -> "Hello /hello2");
    }
}
