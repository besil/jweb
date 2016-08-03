package it.besil.web.main;

import it.besil.web.app.sample.HelloWorldApp;
import it.besil.web.jweb.JWeb;

/**
 * Created by besil on 09/06/2016.
 */
public class AppMain {
    public static void main(String[] args) {
        JWeb jweb = new JWeb();

        jweb.addResource(new HelloWorldApp());
//        jweb.addResource(new EchoApp());

        jweb.start();
    }
}
