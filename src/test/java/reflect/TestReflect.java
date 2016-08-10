package reflect;

import it.besil.jweb.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by besil on 09/08/2016.
 */
public class TestReflect {
    @Test
    public void simple() throws IllegalAccessException {
        Example e = new Example();
        Map<String, Object> m = Utils.inspect(e.getClass());
        System.out.println(m);

        Assert.assertEquals(int.class.getSimpleName(), m.get("a"));
        Assert.assertEquals(String.class.getSimpleName(), m.get("b"));
        Assert.assertEquals(Double.class.getSimpleName(), m.get("c"));
        Assert.assertEquals(Object.class.getSimpleName(), m.get("foo"));

        Assert.assertEquals("List[String]", m.get("l"));

        Assert.assertTrue(Map.class.isAssignableFrom(m.get("my").getClass()));
        Map<String, Object> nested = (Map<String, Object>) m.get("my");

        Assert.assertEquals(Integer.class.getSimpleName(), nested.get("i"));
        Assert.assertEquals(double.class.getSimpleName(), nested.get("q"));

        String l = (String) m.get("myl");
        Assert.assertTrue(l.startsWith("List["));

    }


}
