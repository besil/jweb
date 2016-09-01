package marshalling;

import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by besil on 01/09/2016.
 */
public class MarshallApp extends JWebApp {
    public MarshallApp(JWebConfiguration jwebConf) {
        super(jwebConf);
    }

    @Override
    public List<? extends JWebController> getControllers() {
        return Arrays.asList(new GetMarshallingController(getJWebConf()),
                new PostMarshllingController(getJWebConf()));
    }
}
