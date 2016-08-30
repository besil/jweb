package it.besil.jweb.app.payloads;

import spark.Request;
import spark.Response;

import java.lang.reflect.Field;

/**
 * Created by besil on 01/05/2016.
 */
public interface Payload {

    default void init(Request req, Response resp) {
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(this, req.queryParams(field.getName()));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
