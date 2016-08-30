package it.besil.jweb.app.payloads;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by besil on 30/08/2016.
 */
public class PrimitiveTranslator {
    private Map<Class<?>, Function<String, Object>> classToConverter = new HashMap<>();

    public PrimitiveTranslator() {
        classToConverter.put(Boolean.class, s -> Boolean.parseBoolean(s));
        classToConverter.put(Boolean.TYPE, s -> Boolean.parseBoolean(s));
        classToConverter.put(Byte.class, s -> Byte.parseByte(s));
        classToConverter.put(Byte.TYPE, s -> Byte.parseByte(s));
        classToConverter.put(Short.class, s -> Short.parseShort(s));
        classToConverter.put(Short.TYPE, s -> Short.parseShort(s));
        classToConverter.put(Integer.class, s -> Integer.parseInt(s));
        classToConverter.put(Integer.TYPE, s -> Integer.parseInt(s));
        classToConverter.put(Long.class, s -> Long.parseLong(s));
        classToConverter.put(Long.TYPE, s -> Long.parseLong(s));
        classToConverter.put(Float.class, s -> Float.parseFloat(s));
        classToConverter.put(Float.TYPE, s -> Float.parseFloat(s));
        classToConverter.put(Double.class, s -> Double.parseDouble(s));
        classToConverter.put(Double.TYPE, s -> Double.parseDouble(s));
        classToConverter.put(String.class, s -> s);
    }

    public Function<String, Object> getConverter(Class<?> paramTypeToCreate) {
        return classToConverter.get(paramTypeToCreate);
    }
}