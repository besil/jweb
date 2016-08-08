package it.besil.jweb.server.conf;

/**
 * Created by besil on 19/07/2016.
 */
public enum ConfKeys {
    // debugging
    debug("debug"),
    // Database
//    dburl("db.url"), dbuser("db.user"), dbpwd("db.password"),
    // Server configuration
    port("server.port"),
    keystorepath("server.keystorepath"),
    keystorepassword("server.keystorepassword");
//    sessiontimeout("server.session.timeout");

    private final String name;

    ConfKeys(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
