package it.besil.jweb.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
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

    public static class HTMLCharacterEscapes extends CharacterEscapes {
        private final int[] asciiEscapes;

        public HTMLCharacterEscapes() {
            // start with set of characters known to require escaping (double-quote, backslash etc)
            int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
            // and force escaping of a few others:
            esc['<'] = CharacterEscapes.ESCAPE_STANDARD;
            esc['>'] = CharacterEscapes.ESCAPE_STANDARD;
            esc['&'] = CharacterEscapes.ESCAPE_STANDARD;
            esc['\''] = CharacterEscapes.ESCAPE_STANDARD;
            asciiEscapes = esc;
        }

        // this method gets called for character codes 0 - 127
        @Override
        public int[] getEscapeCodesForAscii() {
            return asciiEscapes;
        }

        // and this for others; we don't need anything special here
        @Override
        public SerializableString getEscapeSequence(int ch) {
            // no further escaping (beyond ASCII chars) needed:
            return null;
        }
    }

}
