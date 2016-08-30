package it.besil.jweb.app.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
                if (!(field.isAnnotationPresent(JsonIgnore.class) || field.isAnnotationPresent(NoPayloadMarshalling.class))) {
                    field.setAccessible(true);
                    field.set(this, new PrimitiveTranslator().getConverter(field.getType()).apply(req.queryParams(field.getName())));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
