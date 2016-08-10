package it.besil.jweb.main;

import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.answer.StatusAnswer;
import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.payloads.Payload;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.List;

/**
 * Created by besil on 09/08/2016.
 */
public class SimpleApp extends JWebApp {
    public SimpleApp(JWebConfiguration jwebConf) {
        super(jwebConf);
    }

    @Override
    public List<? extends JWebController> getControllers() {
        return Arrays.asList(new JWebController(getJWebConf()) {
            @Override
            public HttpMethod getMethod() {
                return HttpMethod.get;
            }

            @Override
            public JWebHandler getHandler() {
                return new JWebHandler<SimplePayload, SimpleAnswer>(SimplePayload.class, SimpleAnswer.class) {
                    @Override
                    public SimpleAnswer process(SimplePayload sp) {
                        return new SimpleAnswer(sp.getSimple());
                    }
                };
            }

            @Override
            public String getPath() {
                return "/simple";
            }
        });
    }

    public static class SimpleAnswer extends StatusAnswer {
        private final String simple;
        private final int num = 5;

        public SimpleAnswer(int status, String simple) {
            super(status);
            this.simple = simple;
        }

        public SimpleAnswer(String simple) {
            this(200, simple);
        }
    }

    public static class SimplePayload implements Payload {
        private String simple;
        private int secret;
        private List<String> apps = Arrays.asList("ciao", "mondo");

        @Override
        public void init(Request req, Response resp) {
            this.simple = req.queryParams("simple");
        }

        public String getSimple() {
            return simple;
        }
    }
}
