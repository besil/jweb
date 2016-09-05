package marshalling;

import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.protocol.answer.Answer;
import it.besil.jweb.app.protocol.payloads.queryparams.QueryParamPayload;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;

import java.util.List;

/**
 * Created by besil on 01/09/2016.
 */
public class PostMarshllingController extends JWebController {
    public PostMarshllingController(JWebConfiguration jWebConf) {
        super(jWebConf);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.post;
    }

    @Override
    public JWebHandler getHandler() {
        return new JWebHandler<PostPayload, PostAnswer>(PostPayload.class, PostAnswer.class) {
            @Override
            public PostAnswer process(PostPayload payload) {
                return new PostAnswer();
            }
        };
    }

    @Override
    public String getPath() {
        return "/marshall";
    }

    public static class PostPayload extends QueryParamPayload {
        String foo;
        int bar;
        List<Integer> bars;

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

        public List<Integer> getBars() {
            return bars;
        }

        public void setBars(List<Integer> bars) {
            this.bars = bars;
        }
    }

    public static class PostAnswer implements Answer {
        String foo;
        int bar;
        List<Integer> bars;
    }
}
