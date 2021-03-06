package it.besil.jweb.server.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by besil on 19/07/2016.
 */
public class JWebConfiguration extends Properties {
    public JWebConfiguration() throws IOException {
        this.load(getClass().getClassLoader().getResourceAsStream("jwebserver.properties"));
    }

    public JWebConfiguration(String path) throws IOException {
        this.load(new FileInputStream(path));
    }

    public int getServerPort() {
        return Integer.parseInt(getProperty(ConfKeys.port.getName()));
    }


    @Override
    public synchronized String toString() {
        List<String> keys = this.keySet().stream().map(o -> o.toString()).collect(Collectors.toList());
        Collections.sort(keys);

        return keys
                .stream()
                .map(key -> key + ": " + getProperty(key))
                .collect(Collectors.joining("\n"));
    }

    public String getKeystorePath() {
        return getProperty(ConfKeys.keystorepath.getName());
    }


    public String getKeystorePassword() {
        return getProperty(ConfKeys.keystorepassword.getName());
    }

    public String getDatabaseUrl() {
        return getProperty(ConfKeys.sessiondburl.getName());
    }

    public String getDatabaseUser() {
        return getProperty(ConfKeys.sessiondbuser.getName());
    }


    public String getDatabasePassword() {
        return getProperty(ConfKeys.sessiondbpassword.getName());
    }

    public int getSessionTimeout() {
        return Integer.parseInt(getProperty(ConfKeys.sessiontimeoutduration.getName()));
    }

    public String getCookieName() {
        return getProperty(ConfKeys.sessioncookiename.getName());
    }

    public String getStaticFileLocation() {
        return getProperty(ConfKeys.staticfilelocation.getName());
    }
}
