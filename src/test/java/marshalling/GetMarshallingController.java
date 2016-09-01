package marshalling;

import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;

/**
 * Created by besil on 01/09/2016.
 */
public class GetMarshallingController extends JWebController {
    public GetMarshallingController(JWebConfiguration jwebconf) {
        super(jwebconf);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.get;
    }

    @Override
    public JWebHandler getHandler() {
        return new GetMarshallingHandler(GetMarshallingHandler.SimplePayload.class, GetMarshallingHandler.SimpleAnswer.class);
    }

    @Override
    public String getPath() {
        return "/marshall";
    }
}
