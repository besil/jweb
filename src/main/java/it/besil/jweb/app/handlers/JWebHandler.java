package it.besil.jweb.app.handlers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.besil.jweb.app.answer.Answer;
import it.besil.jweb.app.answer.ErrorAnswer;
import it.besil.jweb.app.commons.session.SessionManager;
import it.besil.jweb.app.commons.session.SessionPayload;
import it.besil.jweb.app.payloads.Payload;
import it.besil.jweb.server.conf.JWebConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import java.lang.reflect.Method;

/**
 * Created by besil on 03/08/2016.
 */
public abstract class JWebHandler<V extends Payload, A extends Answer> implements Route {
    private final Class<V> payloadClass;
    private final Class<A> answerClass;
    private Logger log = LoggerFactory.getLogger(JWebHandler.class);
    private JWebConfiguration jwebconf;

    public JWebHandler(Class<V> payloadClass, Class<A> answerClass) {
        this.payloadClass = payloadClass;
        this.answerClass = answerClass;
//        this.gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    }

    @Override
    public final Object handle(Request request, Response response) throws Exception {
        Answer a = null;
        Payload p = null;
        try {
//            if( payloadClass.isAssignableFrom(SessionPayload.class) )
//                p = payloadClass.getConstructor(SessionManager.class).newInstance(new SessionManager(jwebconf));
//            else
            p = payloadClass.newInstance();
            if (p instanceof SessionPayload)
                ((SessionPayload) p).setSessionManager(new SessionManager(jwebconf));


            Method payloadInit = payloadClass.getMethod("init", Request.class, Response.class);
            payloadInit.invoke(p, new Object[]{request, response});


        } catch (Exception e) {
            e.printStackTrace();
            a = new ErrorAnswer(300, "Error while init payload");
        }

        if (a == null && p != null) {
            try {
                Method m = getClass().getMethod("process", new Class[]{p.getClass()});
                m.setAccessible(true);
                a = (Answer) m.invoke(this, new Object[]{p});
            } catch (Exception e) {
                e.printStackTrace();
                a = new ErrorAnswer(300, "Error while processing payload");
            }
        }

        response.type("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(a);
    }


    public Class<A> getAnswerClass() {
        return answerClass;
    }

    public Class<V> getPayloadClass() {
        return payloadClass;
    }

    public abstract A process(V payload);

    public JWebConfiguration getJWebConf() {
        return jwebconf;
    }

    public void setJWebConf(JWebConfiguration JWebConf) {
        this.jwebconf = JWebConf;
    }
}
