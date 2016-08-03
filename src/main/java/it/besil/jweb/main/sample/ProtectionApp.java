package it.besil.jweb.main.sample;

import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.filter.FilterType;
import it.besil.jweb.app.filter.JWebFilter;
import it.besil.jweb.app.filter.JWebFilterHandler;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by besil on 03/08/2016.
 */
public class ProtectionApp extends JWebApp {

    @Override
    public List<? extends JWebFilter> getFilters() {
        return Arrays.asList(new JWebFilter() {
            @Override
            public JWebFilterHandler getHandler(Service http) {
                return new JWebFilterHandler() {
                    @Override
                    public void handle(Request request, Response response) throws Exception {
                        if (!request.queryParams().contains("ciao"))
                            http.halt("stooooop. Insert ciao query param");
                    }
                };
            }

            @Override
            public String getPath() {
                return "*";
            }

            @Override
            public FilterType getType() {
                return FilterType.before;
            }
        });
    }

}
