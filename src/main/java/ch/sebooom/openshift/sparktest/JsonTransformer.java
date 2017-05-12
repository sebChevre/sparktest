package ch.sebooom.openshift.sparktest;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by sce on 12.05.2017.
 */
public class JsonTransformer implements ResponseTransformer {
    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
