package it.besil.jweb.app.protocol.payloads.queryparams;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.besil.jweb.app.protocol.payloads.Payload;
import it.besil.jweb.app.protocol.payloads.fillstrategy.FillStrategy;
import it.besil.jweb.app.protocol.payloads.fillstrategy.QueryParamStrategy;
import spark.Request;
import spark.Response;

/**
 * Created by besil on 05/09/2016.
 */
public class QueryParamPayload implements Payload {
    @JsonIgnore
    private final FillStrategy strategy;

    public QueryParamPayload() {
        this.strategy = new QueryParamStrategy();
    }


    @Override
    public void init(Request req, Response resp) {
        this.strategy.fill(req, resp, this);
    }
}
