package it.besil.jweb.app.commons;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.hubspot.jinjava.Jinjava;
import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.answer.Answer;
import it.besil.jweb.app.answer.SuccessAnswer;
import it.besil.jweb.app.filter.FilterType;
import it.besil.jweb.app.filter.JWebFilter;
import it.besil.jweb.app.filter.JWebFilterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by besil on 04/08/2016.
 */
public class DynamicContentApp extends JWebApp {
    private static Logger log = LoggerFactory.getLogger(DynamicContentApp.class);
    private final String temtemplateMappingFile;
    private Map<String, String> route2template;

    public DynamicContentApp(String templateMappingFile) throws IOException {
        this.temtemplateMappingFile = templateMappingFile;
        String mappings = Resources.toString(Resources.getResource(templateMappingFile), Charsets.UTF_8);

        route2template = Arrays.asList(mappings.split("\n"))
                .stream()
                .filter(l -> !l.startsWith("#") && l.length() > 0)
                .map(l -> l.replaceAll("\\s+", " "))
                .map(t -> t.split(" "))
                .filter(elem -> elem.length == 2)
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
    }

    protected final boolean shouldReturnHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }


    private final String render(String template, Map<String, Object> bindings) {
        try {
            String t = Resources.toString(Resources.getResource(template), Charsets.UTF_8);
            return new Jinjava().render(t, bindings);
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred rendering template";
        }
    }

    @Override
    public List<? extends JWebFilter> getFilters() {
        return Arrays.asList(new JWebFilter() {
            @Override
            public JWebFilterHandler getHandler(Service http) {
                return new JWebFilterHandler(http) {
                    @Override
                    public Answer process(Request request, Response response) {

                        if (shouldReturnHtml(request)) {
                            response.type("text/html");
                            HashMap<String, Object> bindings = new Gson().fromJson(response.body(), HashMap.class);
                            String template = route2template.get(request.pathInfo());
                            if (template != null) {
                                String content = render(template, bindings);
                                response.body(content);
                            } else {
                                log.info("No template defined for {}. " +
                                        "Check your {} file in resources. " +
                                        "Switching to json response", request.pathInfo(), temtemplateMappingFile);
                                response.type("application/json");
                            }
                        } else {
                            response.type("application/json");
                        }
                        return new SuccessAnswer("status", "ok");
                    }
                };
            }

            @Override
            public String getPath() {
                return "*";
            }

            @Override
            public FilterType getType() {
                return FilterType.after;
            }
        });
    }
}
