package it.besil.jweb.app.answer;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by besil on 03/08/2016.
 */
public class SuccessAnswer extends Answer {
    public SuccessAnswer(Map<String, Object> bindings) {
        super(200, bindings);
    }

    public SuccessAnswer(String key, Object value) {
        this(ImmutableMap.of(key, value));
    }
}
