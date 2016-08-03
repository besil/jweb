package it.besil.web.app.sample;

import it.besil.web.app.JWebApp;
import it.besil.web.app.answer.Answer;
import it.besil.web.app.answer.SuccessAnswer;
import it.besil.web.app.handlers.JWebHandler;
import it.besil.web.app.payloads.Payload;
import it.besil.web.app.resources.HttpMethod;
import it.besil.web.app.resources.JWebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;

import java.util.Arrays;
import java.util.List;

/**
 * Created by besil on 03/08/2016.
 */
public class EchoApp extends JWebApp {

    @Override
    public List<JWebResource> getResources() {
        return Arrays.asList(new JWebResource() {
            @Override
            public HttpMethod getMethod() {
                return HttpMethod.get;
            }

            @Override
            public JWebHandler getHandler() {
                return null;
            }

            @Override
            public String getPath() {
                return "/echo";
            }
        });
    }

    public class EchoPayload implements Payload {
        private String message;

        @Override
        public void init(Request req) {
            this.message = req.queryParams("message");
        }

        public String getMessage() {
            return message;
        }
    }

    public class GetEchoHandler extends JWebHandler<EchoPayload> {
        private Logger log = LoggerFactory.getLogger(GetEchoHandler.class);

        public GetEchoHandler(Class<EchoPayload> payloadClass) {
            super(payloadClass);
        }

        @Override
        public Answer process(EchoPayload ep) {
            log.info("Handling payload");
            return new SuccessAnswer("message", "Echo: " + ep.getMessage());
        }
    }
}
