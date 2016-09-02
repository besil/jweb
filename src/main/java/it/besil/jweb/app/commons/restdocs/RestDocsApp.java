package it.besil.jweb.app.commons.restdocs;

import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.protocol.answer.Answer;
import it.besil.jweb.app.protocol.payloads.EmptyPayload;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;
import it.besil.jweb.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by besil on 09/08/2016.
 */
public class RestDocsApp extends JWebApp {
    private Logger log = LoggerFactory.getLogger(RestDocsApp.class);
    private Set<String> index = new HashSet<>();
    private Map<String, List<MethodSchema>> path2schema = new HashMap<>();

    public RestDocsApp(JWebConfiguration jwebConf) {
        super(jwebConf);
    }

    public List<? extends JWebController> getControllers(JWebApp app) {
        List<JWebController> controllers = new LinkedList<>();
        log.debug("Generating REST documentation for "+app.getClass().getSimpleName());

        try {
            for (JWebController controller : app.getControllers()) {
                log.debug("Fetching: " + controller.getClass());
                JWebHandler handler = controller.getHandler();
                HttpMethod method = controller.getMethod();
                String path = controller.getPath();

                JsonSchema payloadSchema = Utils.generateSchema(handler.getPayloadClass());
                JsonSchema answerSchema = Utils.generateSchema(handler.getAnswerClass());

                MethodSchema ms = new MethodSchema(method, payloadSchema, answerSchema);
                List<MethodSchema> methods = path2schema.getOrDefault(path, new LinkedList<>());
                methods.add(ms);
                path2schema.put(path, methods);

                index.addAll(path2schema.keySet());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        for(String path : path2schema.keySet()) {
            controllers.add( new RestDocController(getJWebConf(), path, path2schema.get(path)) );
        }

        controllers.add(new RestDocsIndex(getJWebConf(), index));
        return controllers;
    }

    public static class MethodSchema {
        private final HttpMethod method;
        private final JsonSchema payloadSchema;
        private final JsonSchema answerSchema;

        public MethodSchema(HttpMethod method, JsonSchema payloadSchema, JsonSchema answerSchema) {
            this.method = method;
            this.payloadSchema = payloadSchema;
            this.answerSchema = answerSchema;
        }
    }

    public static class ListAnswer implements Answer {
        private List<String> docs;

        public ListAnswer(Set<String> docs) {
            this.docs = new LinkedList<>(docs);
            Collections.sort(this.docs);
        }

        public List<String> getDocs() {
            return docs;
        }
    }

    public static class RestDocsIndex extends JWebController {
        private final Set<String> docs;

        public RestDocsIndex(JWebConfiguration jwebconf, Set<String> docs) {
            super(jwebconf);
            this.docs = docs;
        }

        @Override
        public HttpMethod getMethod() {
            return HttpMethod.get;
        }

        @Override
        public JWebHandler getHandler() {
            return new JWebHandler<EmptyPayload, ListAnswer>(EmptyPayload.class, ListAnswer.class) {
                @Override
                public ListAnswer process(EmptyPayload ep) {
                    return new ListAnswer(docs);
                }
            };
        }

        @Override
        public String getPath() {
            return "/restdocs";
        }
    }
}
