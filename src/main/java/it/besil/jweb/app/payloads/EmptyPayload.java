package it.besil.jweb.app.payloads;


import spark.Request;

/**
 * Created by besil on 07/04/2016.
 */
public class EmptyPayload implements Payload {
    @Override
    public void init(Request req) {

    }
}
