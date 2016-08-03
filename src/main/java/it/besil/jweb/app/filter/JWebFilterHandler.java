package it.besil.jweb.app.filter;

import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Created by besil on 03/08/2016.
 */
public abstract class JWebFilterHandler implements Filter {

    @Override
    public abstract void handle(final Request request, final Response response) throws Exception;
}
