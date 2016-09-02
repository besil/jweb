package it.besil.jweb.app.protocol.answer;

/**
 * Created by besil on 30/08/2016.
 */
public class ReturnCodeMessageAnswer implements Answer {
    private String message;
    private int returnCode;

    public ReturnCodeMessageAnswer(int returnCode, String message) {
        this.message = message;
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
