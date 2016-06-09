package it.pinsurance.web.main;

import spark.Service;

/**
 * Created by besil on 09/06/2016.
 */
public class AppMain {
    public static void main(String[] args) {
        Service http = Service.ignite();
        http.port(8080);

        http.get("/", (req, res) -> "ciao mondo");
    }
}
