package it.besil.jweb.app.commons.restdocs;

import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.answer.SuccessAnswer;
import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.payloads.EmptyPayload;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;
import it.besil.jweb.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by besil on 09/08/2016.
 */
public class RestDocsApp extends JWebApp {
    private Logger log = LoggerFactory.getLogger(RestDocsApp.class);
    private List<String> index = new LinkedList<>();

    public RestDocsApp(JWebConfiguration jwebConf) {
        super(jwebConf);
    }

    public List<? extends JWebController> getControllers(JWebApp app) {
        List<JWebController> controllers = new LinkedList<>();
        log.debug("Generating REST documentation for "+app.getClass().getSimpleName());

        try {
            for (JWebController controller : app.getControllers()) {
                JWebHandler handler = controller.getHandler();
                HttpMethod method = controller.getMethod();
                String path = controller.getPath();

                Map<String, Object> payloadMap = Utils.inspect(handler.getPayloadClass());
                Map<String, Object> answerMap = Utils.inspect(handler.getAnswerClass());
                RestDocController rdc = new RestDocController(path, getJWebConf(), method, payloadMap, answerMap);
                controllers.add(rdc);
                index.add(rdc.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        controllers.add(new RestDocsIndex(getJWebConf(), index));
        return controllers;
    }

    public static class ListAnswer extends SuccessAnswer {
        private List<String> docs;

        public ListAnswer(List<String> docs) {
            super("everything ok");
            this.docs = docs;
        }

        public List<String> getDocs() {
            return docs;
        }
    }

    public static class RestDocsIndex extends JWebController {
        private final List<String> docs;

        public RestDocsIndex(JWebConfiguration jwebconf, List<String> docs) {
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
