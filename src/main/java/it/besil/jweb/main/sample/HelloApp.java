package it.besil.jweb.main.sample;

import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.answer.Answer;
import it.besil.jweb.app.answer.SuccessAnswer;
import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.payloads.EmptyPayload;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by besil on 03/08/2016.
 */
public class HelloApp extends JWebApp {

    @Override
    public List<? extends JWebController> getControllers() {
        return Arrays.asList(new JWebController() {
            @Override
            public HttpMethod getMethod() {
                return HttpMethod.get;
            }

            @Override
            public JWebHandler getHandler() {
                return new JWebHandler<EmptyPayload>(EmptyPayload.class) {

                    @Override
                    public Answer process(EmptyPayload payload) {
                        return new SuccessAnswer("message", "hello");
                    }
                };
            }

            @Override
            public String getPath() {
                return "/hello";
            }
        });
    }
}
