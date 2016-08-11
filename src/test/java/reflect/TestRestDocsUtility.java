package reflect;

import it.besil.jweb.main.SimpleApp;
import it.besil.jweb.utils.Utils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by besil on 11/08/2016.
 */
public class TestRestDocsUtility {

    @Test
    public void first() throws IOException {
        System.out.println(Utils.generateSchema(SimpleApp.SimplePayload.class));
    }

}
