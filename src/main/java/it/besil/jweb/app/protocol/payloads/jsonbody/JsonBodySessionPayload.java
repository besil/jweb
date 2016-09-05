package it.besil.jweb.app.protocol.payloads.jsonbody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.besil.jweb.app.commons.session.SessionPayload;
import it.besil.jweb.app.protocol.payloads.fillstrategy.FillStrategy;
import it.besil.jweb.app.protocol.payloads.fillstrategy.JsonBodyStrategy;
import spark.Request;
import spark.Response;

/**
 * Created by besil on 05/09/2016.
 */
public class JsonBodySessionPayload extends SessionPayload {
    @JsonIgnore
    private final FillStrategy strategy;

    public JsonBodySessionPayload() {
        this.strategy = new JsonBodyStrategy();
    }

    @Override
    public void init(Request req, Response resp) {
        super.init(req, resp);
        this.strategy.fill(req, resp, this);
    }
}
