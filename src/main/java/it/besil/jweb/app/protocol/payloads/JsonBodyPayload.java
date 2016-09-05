package it.besil.jweb.app.protocol.payloads;

import it.besil.jweb.utils.Marshaller;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by besil on 05/09/2016.
 */
public class JsonBodyPayload implements Payload {
    public JsonBodyPayload() {

    }

    @Override
    public void init(Request req, Response resp) {
        if (req.body() != null && req.contentType().equals("application/json") && req.body().length() > 0) {
            try {
                Payload converted = Marshaller.mapper().readValue(req.body(), this.getClass());
                Field[] fields = this.getClass().getDeclaredFields();
                try {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object o = field.get(converted);
                        field.set(this, o);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
