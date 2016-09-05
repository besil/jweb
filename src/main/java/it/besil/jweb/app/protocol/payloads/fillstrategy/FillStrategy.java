package it.besil.jweb.app.protocol.payloads.fillstrategy;

import it.besil.jweb.app.protocol.payloads.Payload;
import spark.Request;
import spark.Response;

/**
 * Created by besil on 05/09/2016.
 */
public interface FillStrategy {

    /**
     * Fill @p using @req and or @res, according to the concrete strategy
     *
     * @param req
     * @param res
     * @param p
     */
    void fill(Request req, Response res, Payload p);
}
