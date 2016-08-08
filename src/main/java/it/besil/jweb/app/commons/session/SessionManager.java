package it.besil.jweb.app.commons.session;

import com.google.common.hash.Hashing;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import it.besil.jweb.server.conf.JWebConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by besil on 07/07/2016.
 */
public class SessionManager {
    private final JdbcPooledConnectionSource pool;
    private final Dao<UserSessionBean, String> sessionDao;
    private final int timeoutSeconds;
    private final String cookieName;
    private Logger log = LoggerFactory.getLogger(SessionManager.class);

    public SessionManager(JWebConfiguration conf) throws SQLException {
        this.pool = new JdbcPooledConnectionSource(conf.getDatabaseUrl(), conf.getDatabaseUser(), conf.getDatabasePassword());
        TableUtils.createTableIfNotExists(this.pool, UserSessionBean.class);
        sessionDao = DaoManager.createDao(pool, UserSessionBean.class);
        this.timeoutSeconds = conf.getSessionTimeout();
        this.cookieName = conf.getCookieName();
    }

    public static SessionManager get(JWebConfiguration conf) {
        try {
            return new SessionManager(conf);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return null;
    }

    private String getToken(Request req) {
        String token = req.cookie(cookieName);
        return token;
    }

    public UserSessionBean createSession(Request req, Response res, String sessionMail) {
        Calendar cal = Calendar.getInstance();
        long millis = cal.getTimeInMillis();
        double v = new Random().nextLong();
        String input = String.valueOf(millis) + String.valueOf(v);

        final String key = Hashing.sha256()
                .hashString(input, StandardCharsets.UTF_8)
                .toString();

        cal.add(Calendar.SECOND, timeoutSeconds);
        Date expirationTime = cal.getTime();

        UserSessionBean usb = new UserSessionBean();
        usb.setSessionkey(key);
        usb.setDuration(timeoutSeconds);
        usb.setExpirationTime(expirationTime);
        usb.setSessionMail(sessionMail);

        try {
            sessionDao.create(usb);
            res.cookie(cookieName, key, timeoutSeconds);
            return usb;
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return new InvalidSessionBean();
    }

    public UserSessionBean getSession(Request req) {
        String token = getToken(req);
        try {
            UserSessionBean found = sessionDao
                    .queryBuilder()
                    .where()
                    .eq("sessionkey", token)
                    .queryForFirst();
            return found != null ? found : new InvalidSessionBean();
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return new InvalidSessionBean();
    }

    public UserSessionBean invalidateSession(Request req, Response res) {
        String token = getToken(req);
        try {
            UserSessionBean user = getSession(req);
            int rowsupdated = sessionDao.deleteById(token);
            res.removeCookie(cookieName);
            return user;
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return new InvalidSessionBean();
    }
}
