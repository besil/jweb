package it.besil.jweb.server.conf;

/**
 * Created by besil on 19/07/2016.
 */
public enum ConfKeys {
    // debugging
    debug("debug"),

    // Server configuration
    port("server.port"),
    keystorepath("server.keystorepath"),
    keystorepassword("server.keystorepassword"),
    staticfilelocation("server.resources.staticfilelocation"),

    // Session app configuration
    sessiondburl("session.db.url"),
    sessiondbuser("session.db.user"),
    sessiondbpassword("session.db.password"),
    sessiontimeoutduration("session.timeout.duration"),
    sessioncookiename("session.cookie.name");

    private final String name;

    ConfKeys(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
