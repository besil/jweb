package it.besil.jweb.app.answer;

/**
 * Created by besil on 03/08/2016.
 */
public class SuccessAnswer extends StatusAnswer {
    private final String message;

    public SuccessAnswer(int status, String message) {
        super(status);
        this.message = message;
    }

    public SuccessAnswer(String message) {
        this(200, message);
    }

    public final String getMessage() {
        return message;
    }

}
