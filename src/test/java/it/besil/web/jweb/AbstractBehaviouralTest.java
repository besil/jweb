package it.besil.web.jweb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import it.besil.jweb.server.JWebServer;
import it.besil.jweb.server.conf.JWebConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by besil on 20/07/2016.
 */
public abstract class AbstractBehaviouralTest {
    private final Logger log = LoggerFactory.getLogger(AbstractBehaviouralTest.class);
    private static JWebServer server;
    private static JWebConfiguration conf;
//    private MyCardsClient restClient;

    @BeforeClass
    public static void initServer() throws Exception {
//        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        if (server == null) {
            conf = new JWebConfiguration();
            server = new JWebServer(conf);
            server.start();
        }
    }

//    @AfterClass
//    public static void stopServer() {
//        server.stop();
//        server = null;
//    }

    @Before
    public void setUp() throws Exception {
//        Model.initForDevelopment(conf);
//        restClient = new MyCardsClient("https://localhost", conf);
    }

    @After
    public void tearDown() throws IOException {
//        Model.getDatabaseManager(conf).dropDatabase();
    }

//    public MyCardsClient getRestClient() {
//        return restClient;
//    }

    protected String prettify(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        return gson.toJson(je);
    }
}
