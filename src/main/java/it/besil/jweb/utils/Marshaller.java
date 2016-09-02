package it.besil.jweb.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Created by besil on 02/09/2016.
 */
public class Marshaller {
    private static final ObjectMapper mapper = Marshaller.mapper();

    public static final synchronized ObjectReader reader() {
        return mapper.reader();
    }

    public static final synchronized ObjectWriter writer() {
        return mapper.writer();
    }

    public static final synchronized ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return mapper;
    }


}
