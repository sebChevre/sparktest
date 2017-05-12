package ch.sebooom.openshift.sparktest.models;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

/**
 * Created by sce on 12.05.2017.
 * Element technologique faisant partie du radar
 */
public class Element {

    private String id;
    private String name;
    private String description;

    public Element(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.name = dbObject.getString("name");
        this.description = dbObject.getString("description");
    }

    public String description(){
        return description;
    }

    public String name() {
        return name;
    }


}
