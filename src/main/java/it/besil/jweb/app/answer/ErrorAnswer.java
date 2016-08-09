package it.besil.jweb.app.answer;

/**
 * Created by besil on 06/07/2016.
 */
public final class ErrorAnswer extends Answer {
    private final String message;

    public ErrorAnswer(int status, String message) {
        super(status);
        this.message = message;
    }

    public ErrorAnswer(String message) {
        this(400, message);
    }

    public final String getMessage() {
        return message;
    }

    //    public ErrorAnswer(Map<String, Object> bindings) {
//        super(400, bindings);
//    }

//    public ErrorAnswer(int status, String message) {
//        super(status, ImmutableMap.of("error", message));
//    }
}
