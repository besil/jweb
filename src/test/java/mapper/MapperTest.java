package mapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by besil on 02/09/2016.
 */
public class MapperTest {
    @Test
    public void test() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        Pojo p = new Pojo();
        p.setFoo("foo");
        p.setBar(1);
        String s = mapper.writeValueAsString(p);

        s = "{ \"foo\": \"foo2\", \"bar\": 2, \"d\": 2.0}";
        Pojo pojo = mapper.readValue(s, Pojo.class);
    }

    public static class Pojo {
        String foo;
        int bar;
        double d;

        public double getD() {
            return d;
        }

        public void setD(double d) {
            this.d = d;
        }

        public String getFoo() {
            return foo;
        }

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public int getBar() {
            return bar;
        }

        public void setBar(int bar) {
            this.bar = bar;
        }
    }
}
