package ch.sebooom.openshift.sparktest;

import static spark.Spark.get;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
public class Server {

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");

    }
}
