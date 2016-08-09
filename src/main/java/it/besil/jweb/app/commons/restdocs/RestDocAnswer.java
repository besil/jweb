package it.besil.jweb.app.commons.restdocs;

import it.besil.jweb.app.answer.Answer;

import java.util.Map;

/**
 * Created by besil on 09/08/2016.
 */
public class RestDocAnswer extends Answer {
    private final String path;
    private final String method;
    private final Map<String, Object> request;
    private final Map<String, Object> response;

    public RestDocAnswer(String path, String method, Map<String, Object> request, Map<String, Object> response) {
        super(SUCCESS);
        this.path = path;
        this.method = method;
        this.request = request;
        this.response = response;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, Object> getRequest() {
        return request;
    }

    public Map<String, Object> getResponse() {
        return response;
    }
}
