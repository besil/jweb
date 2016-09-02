package marshalling;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.besil.jweb.testing.AbstractBehaviouralTest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Created by besil on 01/09/2016.
 */
public class MarshallingTests extends AbstractBehaviouralTest {
    @Before
    public void setup() {
        addApp(new MarshallApp(getJWebConf()));
    }

    @Test
    public void testget() throws UnirestException, JSONException {
        GetMarshallingHandler.SimplePayload sp = new GetMarshallingHandler.SimplePayload();
        sp.foo = "foo";
        sp.bar = 4;
        sp.zdt = ZonedDateTime.now(ZoneOffset.UTC);
        sp.foos = Arrays.asList("foo1", "foo2", "foo3");

        JSONObject resp = Unirest.get(getUrl("/marshall"))
                .queryString("foo", sp.foo)
                .queryString("bar", sp.bar)
                .queryString("zdt", sp.zdt)
                .queryString("foos", sp.foos)
                .asJson().getBody().getObject();

        Assert.assertEquals(sp.foo, resp.getString("foo"));
        Assert.assertEquals(sp.bar, resp.getInt("bar"));
        Assert.assertEquals(sp.zdt, ZonedDateTime.parse(resp.getString("zdt"), DateTimeFormatter.ISO_DATE_TIME));

        Assert.assertEquals(sp.foos.size(), resp.getJSONArray("foos").length());
        for (int i = 0; i < resp.getJSONArray("foos").length(); i++) {
            String el = resp.getJSONArray("foos").getString(i);
            Assert.assertTrue(sp.foos.contains(el));
        }

    }

    @Test
    public void testpost() throws UnirestException, JSONException {
        PostMarshllingController.PostPayload pp = new PostMarshllingController.PostPayload();
        pp.setBar(2);
        pp.setBars(Arrays.asList(1, 2, 3));
        pp.setFoo("foo");

        JSONObject json = new JSONObject();
        json.put("bars", pp.getBars());
        json.put("bar", pp.getBar());


        JSONObject resp = Unirest.post(getUrl("/marshall"))
                .queryString("foo", pp.getFoo())
                .body(json)
                .asJson().getBody().getObject();

        Assert.assertEquals(pp.getBar(), resp.getInt("bar"));
        Assert.assertEquals(pp.getFoo(), resp.getString("foo"));
//        Assert.assertEquals(pp.getBars(), );
        Assert.assertEquals(pp.getBar(), Arrays.asList(resp.getJSONArray("bars")));
    }

}
