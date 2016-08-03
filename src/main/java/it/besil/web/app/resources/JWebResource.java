package it.besil.web.app.resources;

import it.besil.web.app.handlers.JWebHandler;

/**
 * Created by besil on 03/08/2016.
 */
public interface JWebResource {
    HttpMethod getMethod();

    JWebHandler getHandler();

    String getPath();
}
