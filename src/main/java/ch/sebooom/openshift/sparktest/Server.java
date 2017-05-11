package ch.sebooom.openshift.sparktest;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
public class Server {

    public static void main(String[] args) {

        port(8080);

        get("/", (req, res) -> "maingit add .");

        get("/hello", (req, res) -> "Hello World");

        get("/hello2", (req, res) -> "Hello World");

    }
}
