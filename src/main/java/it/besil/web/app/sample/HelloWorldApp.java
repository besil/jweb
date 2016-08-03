package it.besil.web.app.sample;

import it.besil.web.app.JWebApp;
import it.besil.web.app.answer.Answer;
import it.besil.web.app.answer.SuccessAnswer;
import it.besil.web.app.handlers.JWebHandler;
import it.besil.web.app.payloads.EmptyPayload;
import it.besil.web.app.resources.HttpMethod;
import it.besil.web.app.resources.JWebResource;

import java.util.Arrays;
import java.util.List;

/**
 * Created by besil on 03/08/2016.
 */
public class HelloWorldApp extends JWebApp {

    @Override
    public List<JWebResource> getResources() {
        return Arrays.asList(new JWebResource() {
            @Override
            public HttpMethod getMethod() {
                return HttpMethod.get;
            }

            @Override
            public JWebHandler getHandler() {
                return new HelloWorldHandler();
            }

            @Override
            public String getPath() {
                return "/hello";
            }
        });
    }

    public class HelloWorldHandler extends JWebHandler<EmptyPayload> {
        public HelloWorldHandler() {
            super(EmptyPayload.class);
        }

        @Override
        public Answer process(EmptyPayload payload) {
            return new SuccessAnswer("message", "hello world");
        }
    }
}
