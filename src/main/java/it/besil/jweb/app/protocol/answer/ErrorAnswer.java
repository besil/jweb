package it.besil.jweb.app.protocol.answer;

/**
 * Created by besil on 06/07/2016.
 */
public class ErrorAnswer extends ReturnCodeMessageAnswer {

    public ErrorAnswer(int returnCode, String message) {
        super(returnCode, message);
    }

    public ErrorAnswer(String message) {
        this(400, message);
    }
}
