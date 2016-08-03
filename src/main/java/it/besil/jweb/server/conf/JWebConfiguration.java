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

    public boolean debugMode() {
        return Boolean.parseBoolean(getProperty(ConfKeys.debug.getName()));
    }

    public String getDatabaseUrl() {
        return getProperty(ConfKeys.dburl.getName());
    }

    public String getDatabaseUser() {
        return getProperty(ConfKeys.dbuser.getName());
    }

    public String getDatabasePassword() {
        return getProperty(ConfKeys.dbpwd.getName());
    }

    public int getServerPort() {
        return Integer.parseInt(getProperty(ConfKeys.port.getName()));
    }

    public int getSessionTimeout() {
        return Integer.parseInt(getProperty(ConfKeys.sessiontimeout.getName()));
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


}
