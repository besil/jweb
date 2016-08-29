package it.besil.jweb.app.answer;

/**
 * Created by besil on 10/08/2016.
 */
public class StatusAnswer implements Answer {
    private int status;

    public StatusAnswer(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}