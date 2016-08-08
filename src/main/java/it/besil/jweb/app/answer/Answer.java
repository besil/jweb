package it.besil.jweb.app.answer;

import java.util.Map;

/**
 * Created by besil on 01/05/2016.
 */
public abstract class Answer {
    public static final int SUCCESS = 200;
    private final int status;
    private final Map<String, Object> bindings;

    public Answer(int status, Map<String, Object> bindings) {
        this.status = status;
        this.bindings = bindings;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Answer)
            return this.equals((Answer) obj);
        return super.equals(obj);
    }

    public boolean equals(Answer a) {
        return this.getStatus() == a.getStatus() && this.bindings.equals(a.bindings);
    }
}
