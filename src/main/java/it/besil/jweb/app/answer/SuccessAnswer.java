package it.besil.jweb.app.answer;

/**
 * Created by besil on 03/08/2016.
 */
public class SuccessAnswer extends ReturnCodeMessageAnswer {
    private final String message;

    public SuccessAnswer(int status, String message) {
        super(status, message);
        this.message = message;
    }

    public SuccessAnswer(String message) {
        this(200, message);
    }

}
