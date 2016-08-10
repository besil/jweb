package it.besil.jweb.app.commons.restdocs;

import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.payloads.EmptyPayload;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;

import java.util.Map;

/**
 * Created by besil on 09/08/2016.
 */
public class RestDocController extends JWebController {
    private final String path;
    private final String method;
    private final Map<String, Object> payload;
    private final Map<String, Object> answer;

    public RestDocController(String path, JWebConfiguration jWebConf, HttpMethod method, Map<String, Object> payloadMap, Map<String, Object> answerMap) {
        super(jWebConf);
        this.path = path;
        this.method = method.name().toUpperCase();
        this.payload = payloadMap;
        this.answer = answerMap;
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.get;
    }

    @Override
    public JWebHandler getHandler() {
        return new JWebHandler<EmptyPayload, RestDocAnswer>(EmptyPayload.class, RestDocAnswer.class) {
            @Override
            public RestDocAnswer process(EmptyPayload ep) {
                return new RestDocAnswer(path, method, payload, answer);
            }
        };
    }

    @Override
    public String getPath() {
        return "/restdocs" + this.path;
    }
}
