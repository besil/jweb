package it.besil.jweb.app.commons.session;

import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.answer.Answer;
import it.besil.jweb.app.answer.ErrorAnswer;
import it.besil.jweb.app.answer.SuccessAnswer;
import it.besil.jweb.app.filter.FilterType;
import it.besil.jweb.app.filter.JWebFilter;
import it.besil.jweb.app.filter.JWebFilterHandler;
import it.besil.jweb.server.conf.JWebConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by besil on 08/08/2016.
 */
public class SessionManagerApp extends JWebApp {
    private static final Logger log = LoggerFactory.getLogger(SessionManagerApp.class);
    private final String path2lock;

    public SessionManagerApp(JWebConfiguration conf, String path2lock) {
        super(conf);
        this.path2lock = path2lock;
    }

    @Override
    public List<? extends JWebFilter> getFilters() {
        return Arrays.asList(new JWebFilter() {
            @Override
            public JWebFilterHandler getHandler(Service http) {
                return new JWebFilterHandler(http) {
                    @Override
                    public Answer process(Request request, Response response) {
                        try {
                            SessionManager sm = new SessionManager(getJWebConf());
                            UserSessionBean usb = sm.getSession(request);
                            Date currentTime = new Date();
                            Date expirationTime = usb.getExpirationTime();

                            log.trace("Analyzing request {}", usb);
                            log.trace("Session is: {}", usb.getClass());
                            log.trace("Current time: {}", currentTime);
                            log.trace("Expiration time: {}", expirationTime);

                            if (usb instanceof InvalidSessionBean)
                                log.trace("{} is not valid", usb);
                            if (usb.getExpirationTime() == null || usb.getExpirationTime().after(new Date()))
                                log.trace("{} is expired", usb);

                            log.trace("Expiration time: {}", usb.getExpirationTime());

                            if (usb instanceof InvalidSessionBean || usb.getExpirationTime().before(new Date())) {
                                log.trace("Session is shit. Redirecting to login");
                                try {
                                    sm.invalidateSession(request, response);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
//                               http.halt(200, "must login");
                                return new ErrorAnswer("must login");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return new SuccessAnswer("session valid");
                    }
                };
            }

            @Override
            public String getPath() {
                return path2lock;
            }

            @Override
            public FilterType getType() {
                return FilterType.before;
            }
        });
    }
}
