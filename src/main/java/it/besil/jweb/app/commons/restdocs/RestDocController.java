package it.besil.jweb.app.commons.restdocs;

import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.payloads.EmptyPayload;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;

import java.util.List;
import java.util.Map;

/**
 * Created by besil on 09/08/2016.
 */
public class RestDocController extends JWebController {
    private final String path;
    private final List<RestDocsApp.MethodSchema> schema;

    public RestDocController(JWebConfiguration jWebConf, String path, List<RestDocsApp.MethodSchema> methodSchemas) {
        super(jWebConf);
        this.path = path;
        this.schema = methodSchemas;
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
                return new RestDocAnswer(path, schema);
            }
        };
    }

    @Override
    public String getPath() {
        return "/restdocs" + this.path;
    }
}
