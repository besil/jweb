package it.besil.jweb.app.commons.session;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by besil on 07/07/2016.
 */
@DatabaseTable(tableName = "USER_SESSION")
public class UserSessionBean {
    @DatabaseField(id = true)
    private String sessionkey;
    @DatabaseField(canBeNull = false)
    private Date expirationTime;
    @DatabaseField(canBeNull = false)
    private int duration;
    @DatabaseField(canBeNull = false)
    private String sessionMail;

    @Override
    public String toString() {
        return sessionkey + "-" + expirationTime + "-" + duration + "-" + sessionMail;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSessionMail() {
        return sessionMail;
    }

    public void setSessionMail(String sessionMail) {
        this.sessionMail = sessionMail;
    }
}
