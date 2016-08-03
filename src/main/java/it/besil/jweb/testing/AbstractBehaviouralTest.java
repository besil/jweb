package it.besil.jweb.testing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import it.besil.jweb.app.JWebApp;
import it.besil.jweb.server.JWebServer;
import it.besil.jweb.server.conf.JWebConfiguration;
import org.junit.BeforeClass;

/**
 * Created by besil on 20/07/2016.
 */
public abstract class AbstractBehaviouralTest {
    private static JWebServer server;
    private static JWebConfiguration conf;

    @BeforeClass
    public static void initServer() throws Exception {
        if (server == null) {
            conf = new JWebConfiguration();
            server = new JWebServer(conf);
        }
    }

    protected String getUrl(String path) {
        return "http://localhost:" + conf.getServerPort() + path;
    }


    protected String prettify(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        return gson.toJson(je);
    }

    protected void addApp(JWebApp app) {
        server.addApp(app);
    }
}
