package ch.sebooom.openshift.sparktest.services;

import ch.sebooom.openshift.sparktest.models.Element;
import com.google.gson.Gson;
import com.mongodb.*;
import com.sun.xml.internal.bind.v2.TODO;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sce on 12.05.2017.
 */
public class ElementService {

    private final DB db;
    private final DBCollection collection;

    public ElementService(DB db){
        this.db = db;
        this.collection = db.getCollection("element");
    }
    public List<Element> findAll() {
        List<Element> todos = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            todos.add(new Element((BasicDBObject) dbObject));
        }
        return todos;
    }

    public void createNewElement(String body) {

        System.out.print(body);

        Element el = new Gson().fromJson(body, Element.class);
        collection.insert(new BasicDBObject("name", el.name()).append("description", el.description()));
    }

    public Element find(String id) {
        return new Element((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

    //TODO voir pour activer fonctionnalité
    /*
    public Element update(String todoId, String body) {
        Element el = new Gson().fromJson(body, Element.class);
        collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("done", todo.isDone())));
        return this.find(todoId);
    }
    */

}
