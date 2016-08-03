package it.besil.jweb.app.filter;

import spark.Service;

/**
 * Created by besil on 03/08/2016.
 */
public abstract class JWebFilter {
    public abstract JWebFilterHandler getHandler(final Service http);

    public abstract String getPath();

    public abstract FilterType getType();
}
