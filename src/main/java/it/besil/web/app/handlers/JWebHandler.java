package it.besil.web.app.handlers;

import com.google.gson.Gson;
import it.besil.web.app.answer.Answer;
import it.besil.web.app.answer.ErrorAnswer;
import it.besil.web.app.payloads.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import java.lang.reflect.Method;

/**
 * Created by besil on 03/08/2016.
 */
public abstract class JWebHandler<V extends Payload> implements Route {
    private final Class<V> payloadClass;
    private Logger log = LoggerFactory.getLogger(JWebHandler.class);

    public JWebHandler(Class<V> payloadClass) {
        this.payloadClass = payloadClass;
    }

    @Override
    public final Object handle(Request request, Response response) throws Exception {
        Answer a = null;
        Payload p = null;
        try {
            p = payloadClass.newInstance();
            Method payloadInit = payloadClass.getMethod("init", Request.class); //, Response.class);
            payloadInit.invoke(p, new Object[]{request});
        } catch (Exception e) {
            e.printStackTrace();
            a = new ErrorAnswer(300, "Error while init payload");
        }

        if (a == null | p == null) {
            try {
                Method m = getClass().getMethod("process", new Class[]{p.getClass()});
//            m.setAccessible(true);
                a = (Answer) m.invoke(this, new Object[]{p});
            } catch (Exception e) {
                e.printStackTrace();
                a = new ErrorAnswer(300, "Error while processing payload");
            }
        }

        response.type("application/json");
        return new Gson().toJson(a.getBindings());

    }

    public abstract Answer process(V payload);
}
