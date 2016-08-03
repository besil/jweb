package it.besil.web.app.answer;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by besil on 06/07/2016.
 */
public class ErrorAnswer extends Answer {
    public ErrorAnswer(String message) {
        this(400, message);
    }

    public ErrorAnswer(Map<String, Object> bindings) {
        super(400, bindings);
    }

    public ErrorAnswer(int status, String message) {
        super(status, ImmutableMap.of("error", message));
    }
}
