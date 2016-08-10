package it.besil.jweb.main;

import it.besil.jweb.app.commons.restdocs.RestDocsApp;
import it.besil.jweb.server.JWebServer;
import it.besil.jweb.server.conf.JWebConfiguration;

import java.io.IOException;

/**
 * Created by besil on 09/08/2016.
 */
public class SimpleMain {
    public static void main(String[] args) throws IOException {
        JWebConfiguration jwconf = new JWebConfiguration();
        JWebServer jweb = new JWebServer(jwconf);

        jweb.addApp(new RestDocsApp(jwconf));
        jweb.addApp(new SimpleApp(jwconf));
    }
}
