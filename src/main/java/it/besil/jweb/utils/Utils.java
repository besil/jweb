package it.besil.jweb.utils;

import it.besil.jweb.app.commons.restdocs.NoRestDocs;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by besil on 09/08/2016.
 */
public class Utils {
    private static boolean isSystemClass(Class<?> t) {
        return t.isPrimitive()
                || t.isAssignableFrom(Double.class) || t.isAssignableFrom(Float.class)
                || t.isAssignableFrom(Integer.class) || t.isAssignableFrom(Long.class)
                || t.isAssignableFrom(Character.class) || t.isAssignableFrom(Byte.class)
                || t.isAssignableFrom(Short.class) || t.isAssignableFrom(Boolean.class)
                || t.isAssignableFrom(String.class);
    }

    private static String type(Class<?> t) {
        if (t.isArray())
            return "ARRAY";
        if (isSystemClass(t))
            return "PRIMITIVE";
        if (Collection.class.isAssignableFrom(t))
            return "COLLECTION";
        // TODO primitive array
        return "CUSTOM";
    }

    public static Map<String, Object> inspect(Class<?> clazz) throws IllegalAccessException {
        Map<String, Object> m = new HashMap<>();
//        System.out.println(clazz);
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            NoRestDocs noRestDocs = f.getAnnotation(NoRestDocs.class);
            if (noRestDocs == null) {
                Class<?> t = f.getType();
//            System.out.println(f.getName() + " --> " + type(t));

                switch (type(t)) {
                    case "PRIMITIVE":
                        m.put(f.getName(), f.getType().getSimpleName());
                        break;
                    case "ARRAY":
//                    String arrayformat = "%s";
                        String data;
//                    if (type(t.getComponentType()).equals("PRIMITIVE"))
                        data = t.getComponentType().getSimpleName();
                        while (data.contains("[]")) {
                            data = data.replace("[]", "");
                            data = "List[" + data + "]";
                        }
//                    else {
//                        Class<?> tmp = t;
//                        while (type(tmp.getComponentType()).equals("ARRAY")) {
//                            tmp = tmp.getComponentType();
//                            arrayformat = "List[" + arrayformat + "]";
//                        }
//                    data = inspect(t.getComponentType()).toString();
//            }
//                    m.put(f.getName(), arrayformat.format(data));
                        m.put(f.getName(), "List[" + data + "]");
                        break;
                    case "COLLECTION":
                        Type type = f.getGenericType();
                        String format = type instanceof ParameterizedType ? "%s" : "List<%s>";
                        while (type instanceof ParameterizedType) {
                            ParameterizedType pt = (ParameterizedType) type;
                            type = pt.getActualTypeArguments()[0];
                            format = "List[" + format + "]";
                        }

                        Class<?> clzz = (Class<?>) type;
                        if (type(clzz).equals("PRIMITIVE")) {
                            m.put(f.getName(), String.format(format, clzz.getSimpleName()));
                        } else {
                            Map<String, Object> nested = inspect(clzz);
                            m.put(f.getName(), String.format(format, nested));
                        }

                        break;
                    case "CUSTOM":
                        Map<String, Object> nestedObj = inspect(f.getType());
                        m.put(f.getName(), nestedObj);
                        break;
                    default:
                        System.out.println("BOH! " + f.getClass());
                        break;
                }
            }
        }

        return m;
    }
}
