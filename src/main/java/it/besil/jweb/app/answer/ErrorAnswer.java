package it.besil.jweb.app.answer;

/**
 * Created by besil on 06/07/2016.
 */
public class ErrorAnswer extends StatusAnswer {
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
}
