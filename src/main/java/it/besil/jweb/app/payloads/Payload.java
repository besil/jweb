package it.besil.jweb.app.payloads;

import spark.Request;
import spark.Response;

/**
 * Created by besil on 01/05/2016.
 */
public interface Payload {

    void init(Request req, Response resp);

}
