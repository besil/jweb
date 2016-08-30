package it.besil.jweb.app.answer;

/**
 * Created by besil on 10/08/2016.
 */
public class ReturnCodeAnswer implements Answer {
    private int returnCode;

    public ReturnCodeAnswer(int returnCode) {
        this.returnCode = returnCode;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
