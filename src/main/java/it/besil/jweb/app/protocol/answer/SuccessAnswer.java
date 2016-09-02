package it.besil.jweb.app.protocol.answer;

/**
 * Created by besil on 03/08/2016.
 */
public class SuccessAnswer extends ReturnCodeMessageAnswer {
    public SuccessAnswer(int status, String message) {
        super(status, message);
    }

    public SuccessAnswer(String message) {
        this(200, message);
    }
}
