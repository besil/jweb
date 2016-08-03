package it.besil.web.app.sample;

import it.besil.web.app.JWebApp;
import it.besil.web.app.answer.Answer;
import it.besil.web.app.answer.SuccessAnswer;
import it.besil.web.app.handlers.JWebHandler;
import it.besil.web.app.payloads.Payload;
import it.besil.web.app.resources.HttpMethod;
import it.besil.web.app.resources.JWebResource;
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
                return new GetEchoHandler();
            }

            @Override
            public String getPath() {
                return "/echo";
            }
        });
    }

    public static class EchoPayload implements Payload {
        private String message;

        public EchoPayload() {

        }

        @Override
        public void init(Request req) {
            this.message = req.queryParams("message");
        }

        public String getMessage() {
            return message;
        }
    }

    public static class GetEchoHandler extends JWebHandler<EchoPayload> {
//        private Logger log = LoggerFactory.getLogger(GetEchoHandler.class);

        public GetEchoHandler() {
            super(EchoPayload.class);
        }

        @Override
        public Answer process(EchoPayload ep) {
            return new SuccessAnswer("message", "Echo: " + ep.getMessage());
        }
    }
}
