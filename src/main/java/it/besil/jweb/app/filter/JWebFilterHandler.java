package it.besil.jweb.app.filter;

import com.google.gson.Gson;
import it.besil.jweb.app.answer.Answer;
import it.besil.jweb.app.answer.ErrorAnswer;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Service;

import java.lang.reflect.Method;

/**
 * Created by besil on 03/08/2016.
 */
public abstract class JWebFilterHandler implements Filter {
    private final Service http;

    public JWebFilterHandler(Service http) {
        this.http = http;
    }

    @Override
    public final void handle(final Request request, final Response response) throws Exception {
        Answer a;
        try {
            Method m = getClass().getMethod("process", Request.class, Response.class);
            m.setAccessible(true);
            a = (Answer) m.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            a = new ErrorAnswer(300, "Error while processing payload");
        }

        if (a instanceof ErrorAnswer) {
            String message = new Gson().toJson(a.getBindings());
            response.type("application/json");
            http.halt(message);
        }
    }

    public abstract Answer process(Request request, Response response);
}
