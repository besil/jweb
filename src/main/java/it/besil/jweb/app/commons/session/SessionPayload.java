package it.besil.jweb.app.commons.session;

import it.besil.jweb.app.commons.restdocs.NoRestDocs;
import it.besil.jweb.app.payloads.Payload;
import spark.Request;
import spark.Response;

/**
 * Created by besil on 10/08/2016.
 */
public class SessionPayload implements Payload {
    @NoRestDocs
    private SessionManager sessionManager;
    @NoRestDocs
    private String sessionId;

    public final SessionManager getSessionManager() {
        return sessionManager;
    }

    public final void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public final String getSessionId() {
        return sessionId;
    }

    public final void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public void init(Request req, Response resp) {
        this.sessionId = sessionManager.getSession(req).getSessionId();
    }
}
