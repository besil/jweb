package it.besil.jweb.app.commons.restdocs;

import it.besil.jweb.app.protocol.answer.Answer;

import java.util.List;

/**
 * Created by besil on 09/08/2016.
 */
public class RestDocAnswer implements Answer {
    private final String path;
    private final List<RestDocsApp.MethodSchema> schema;

    public RestDocAnswer(String path, List<RestDocsApp.MethodSchema> schema) {
        this.path = path;
        this.schema = schema;
    }

    public String getPath() {
        return path;
    }

    public List<RestDocsApp.MethodSchema> getSchema() {
        return schema;
    }
}
