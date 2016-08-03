package it.besil.jweb.main;

import it.besil.jweb.main.sample.GoodMorningApp;
import it.besil.jweb.main.sample.HelloApp;
import it.besil.jweb.main.sample.ProtectionApp;
import it.besil.jweb.server.JWebServer;
import it.besil.jweb.server.conf.JWebConfiguration;

import java.io.IOException;

/**
 * Created by besil on 03/08/2016.
 */
public class JWebMain {
    public static void main(String[] args) throws IOException {
        JWebServer jweb = new JWebServer(new JWebConfiguration());

        jweb.addApp(new ProtectionApp());
        jweb.addApp(new HelloApp());
        jweb.addApp(new GoodMorningApp());
    }
}
