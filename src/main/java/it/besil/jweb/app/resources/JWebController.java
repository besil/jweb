package it.besil.jweb.app.resources;

import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.server.conf.JWebConfiguration;

/**
 * Created by besil on 03/08/2016.
 */
public abstract class JWebController {
    private final JWebConfiguration jwebconf;

    public JWebController(JWebConfiguration jwebconf) {
        this.jwebconf = jwebconf;
    }

    public abstract HttpMethod getMethod();

    public abstract JWebHandler getHandler();

    public abstract String getPath();

    public JWebConfiguration getJWebConfiguration() {
        return this.jwebconf;
    }
}
