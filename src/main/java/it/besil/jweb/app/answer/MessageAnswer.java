package it.besil.jweb.app.answer;

/**
 * Created by besil on 10/08/2016.
 */
public class MessageAnswer implements Answer {
    private String message;

    public MessageAnswer(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
