package it.besil.jweb.app.answer;

/**
 * Created by besil on 06/07/2016.
 */
public class ErrorAnswer extends ReturnCodeAnswer {
    private final String message;

    public ErrorAnswer(int returnCode, String message) {
        super(returnCode);
        this.message = message;
    }

    public ErrorAnswer(String message) {
        this(400, message);
    }

    public final String getMessage() {
        return message;
    }
}
