package it.besil.jweb.app.protocol.payloads.fillstrategy;

import it.besil.jweb.app.protocol.payloads.Payload;
import it.besil.jweb.utils.Marshaller;
import spark.Request;
import spark.Response;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by besil on 05/09/2016.
 */
public class QueryParamStrategy implements FillStrategy {
    @Override
    public void fill(Request req, Response res, Payload p) {
        if (req.queryParams().size() > 0) {
            Map<String, Object> params = new HashMap<>();
            for (String param : req.queryParams()) {
                String[] values = req.queryParamsValues(param);
                if (values.length == 1)
                    params.put(param, values[0]);
                else
                    params.put(param, values);
            }

            Payload converted = Marshaller.mapper().convertValue(params, p.getClass());
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
        }
    }
}
