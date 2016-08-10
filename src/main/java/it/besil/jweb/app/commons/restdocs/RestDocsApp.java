package it.besil.jweb.app.commons.restdocs;

import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;
import it.besil.jweb.utils.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by besil on 09/08/2016.
 */
public class RestDocsApp extends JWebApp {
    public RestDocsApp(JWebConfiguration jwebConf) {
        super(jwebConf);
    }

    public List<? extends JWebController> getControllers(JWebApp app) {
        List<JWebController> controllers = new LinkedList<>();

        try {
            for (JWebController controller : app.getControllers()) {
                JWebHandler handler = controller.getHandler();
                HttpMethod method = controller.getMethod();
                String path = controller.getPath();

//                System.out.println("INPUT");
//                System.out.println(handler.getPayloadClass());
//                System.out.println(Utils.inspect(handler.getPayloadClass()));
                Map<String, Object> payloadMap = Utils.inspect(handler.getPayloadClass());

//                System.out.println("OUTPUT");
//                System.out.println(handler.getAnswerClass());
//                System.out.println(Utils.inspect(handler.getAnswerClass()));
//                for (Field f : handler.getAnswerClass().getDeclaredFields()) {
//                    f.setAccessible(true);
//                    System.out.println("   " + f.getName() + ": " + f.getType().getSimpleName());
//                }
                Map<String, Object> answerMap = Utils.inspect(handler.getAnswerClass());
                controllers.add(new RestDocController(path, getJWebConf(), method, payloadMap, answerMap));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return controllers;
    }
}
