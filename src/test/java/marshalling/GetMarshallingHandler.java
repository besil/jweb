package marshalling;

import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.protocol.answer.Answer;
import it.besil.jweb.app.protocol.payloads.Payload;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by besil on 01/09/2016.
 */
public class GetMarshallingHandler extends JWebHandler<GetMarshallingHandler.SimplePayload, GetMarshallingHandler.SimpleAnswer> {
    public GetMarshallingHandler(Class<SimplePayload> payloadClass, Class<SimpleAnswer> answerClass) {
        super(payloadClass, answerClass);
    }

    @Override
    public SimpleAnswer process(SimplePayload sp) {
        SimpleAnswer sa = new SimpleAnswer();
        sa.foo = sp.foo;
        sa.bar = sp.bar;
        sa.zdt = sp.zdt;
        sa.foos = sp.foos;
        return sa;
    }

    public static class SimplePayload implements Payload {
        String foo;
        int bar;
        ZonedDateTime zdt;
        List<String> foos;

        public List<String> getFoos() {
            return foos;
        }

        public void setFoos(List<String> foos) {
            this.foos = foos;
        }

        public ZonedDateTime getZdt() {
            return zdt;
        }

        public void setZdt(ZonedDateTime zdt) {
            this.zdt = zdt;
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

    public static class SimpleAnswer implements Answer {
        String foo;
        int bar;
        ZonedDateTime zdt;
        List<String> foos;

        public List<String> getFoos() {
            return foos;
        }

        public void setFoos(List<String> foos) {
            this.foos = foos;
        }

        public ZonedDateTime getZdt() {
            return zdt;
        }

        public void setZdt(ZonedDateTime zdt) {
            this.zdt = zdt;
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
