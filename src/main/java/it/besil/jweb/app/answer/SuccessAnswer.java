package it.besil.jweb.app.answer;

/**
 * Created by besil on 03/08/2016.
 */
public final class SuccessAnswer extends Answer {
    private final String message;

    public SuccessAnswer(String message) {
        super(SUCCESS);
        this.message = message;
    }

    public final String getMessage() {
        return message;
    }

}
