package ch.sebooom.openshift.sparktest;

import ch.sebooom.openshift.sparktest.api.ElementResource;
import ch.sebooom.openshift.sparktest.api.ServerTestResource;
import ch.sebooom.openshift.sparktest.services.ElementService;
import com.mongodb.*;

import static spark.Spark.*;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
public class Server {

    private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;

    public static void main(String[] args) throws Exception{

        ipAddress(IP_ADDRESS);
        port(PORT);
        staticFileLocation("/public");

        new ServerTestResource();
        new ElementResource(new ElementService(mongo()));


    }

    private static DB mongo() throws Exception{
        String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");

        if (host == null) {
            MongoClient mongoClient = new MongoClient("localhost");
            return mongoClient.getDB("techno-radar");
        }

        int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
        String dbname = System.getenv("OPENSHIFT_APP_NAME");
        String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");

        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);

        if (db.authenticate(username, password.toCharArray())) {
            return db;
        } else {
            throw new RuntimeException("Not able to authenticate with MongoDB");
        }

    }
}
