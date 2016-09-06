package it.besil.jweb.app.protocol.payloads.fillstrategy;

import it.besil.jweb.app.protocol.payloads.Payload;
import it.besil.jweb.utils.Marshaller;
import org.apache.commons.lang3.StringEscapeUtils;
import spark.Request;
import spark.Response;

import java.lang.reflect.Field;

/**
 * Created by besil on 05/09/2016.
 */
public class JsonBodyStrategy implements FillStrategy {
    @Override
    public void fill(Request req, Response res, Payload p) {
        if (req.body() != null && req.contentType().equals("application/json") && req.body().length() > 0) {
            try {
                String escaped = StringEscapeUtils.escapeHtml4(req.body());
                System.out.println(escaped);
                Payload converted = Marshaller.mapper().readValue(req.body(), p.getClass());
                System.out.println(Marshaller.mapper().writeValueAsString(converted));
                Field[] fields = p.getClass().getDeclaredFields();
                try {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object o = field.get(converted);
                        field.set(p, o);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
