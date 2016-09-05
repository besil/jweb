package it.besil.jweb.app.protocol.payloads;

import spark.Request;
import spark.Response;

/**
 * Created by besil on 01/05/2016.
 */
public interface Payload {

    /**
     * By default, read all query params
     *
     * @param req
     * @param resp
     */
    void init(Request req, Response resp);

}
