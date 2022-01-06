package top.brozen.quarkus.first.support.sqlclient.transform;

import org.limbo.utils.strings.NamingCaseUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @author Brozen
 * @since 2022-01-05
 */
public record TransformMeta<T> (
        Class<T> clazz,
        Constructor<T> constructor,
        Map<String, Field> fields
) {

    private static final Map<Class<?>, TransformMeta<?>> CACHED = new ConcurrentHashMap<>();


    public static <T> TransformMeta<T> parse(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        TransformMeta<T> meta = (TransformMeta<T>) CACHED.computeIfAbsent(clazz, TransformMeta::_parse);
        return meta;
    }


    private static <T> TransformMeta<T> _parse(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();

            Class<?> cls = clazz;
            Map<String, Field> fields = new HashMap<>();
            while (cls != Object.class) {
                Stream.of(cls.getDeclaredFields())
                        .filter(f -> {
                            int modifiers = f.getModifiers();
                            return !Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers);
                        })
                        .forEach(f -> {
                            String fName = f.getName();
                            fields.put(fName, f);
                            fields.put(NamingCaseUtils.camelToUnderline(fName), f);
                        });
                cls = cls.getSuperclass();
            }

            return new TransformMeta<>(clazz, constructor, Collections.unmodifiableMap(fields));
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Parse meta of type " + clazz.getName() + " failed", e);
        }
    }


}
