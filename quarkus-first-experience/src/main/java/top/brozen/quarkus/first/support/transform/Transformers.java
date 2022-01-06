package top.brozen.quarkus.first.support.transform;

import com.fasterxml.jackson.core.type.TypeReference;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.RowSet;
import org.limbo.utils.tuple.Tuple2;
import org.limbo.utils.tuple.Tuples;
import top.brozen.quarkus.first.support.transform.json.*;
import top.brozen.quarkus.first.support.transform.sqlclient.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 便捷生成{@link RowTransformer}的工具类，内部有缓存。
 *
 * @author Brozen
 * @since 2022-01-05
 */
public class Transformers {


    static final ConcurrentHashMap<Tuple2<Class<?>, Class<?>>, Transformer<?, ?>> CACHED = new ConcurrentHashMap<>();


    /**
     * 创建一个简单的通过反射将{@link RowSet}封装单个Bean的转换器。
     *
     * @param entityClass 要封装的Bean类类型
     * @param <T> 要封装的Bean类型
     * @return 转换器
     * @see ReflectionRowTransformer
     * @see SimpleReflectionRowTransformer
     */
    public static <T> RowTransformer<T> rowForClass(Class<T> entityClass) {
        Tuple2<Class<?>, Class<?>> cacheKey = Tuples.of(RowTransformer.class, entityClass);

        @SuppressWarnings("unchecked")
        RowTransformer<T> tRowTransformer = (RowTransformer<T>) CACHED
                .computeIfAbsent(cacheKey, _k -> new SimpleReflectionRowTransformer<>(_k.getB()));
        return tRowTransformer;
    }


    /**
     * 创建一个通过反射将{@link RowSet}封装单个Bean，并返回{@link Uni}的转换器。
     *
     * @param entityClass 要封装的Bean类类型
     * @param <T> 要封装的Bean类型
     * @return 转换器
     * @see UniRowTransformer
     * @see ReflectionRowTransformer
     * @see UniReflectionRowTransformer
     */
    public static <T> UniRowTransformer<T> uniRowForClass(Class<T> entityClass) {
        Tuple2<Class<?>, Class<?>> cacheKey = Tuples.of(UniRowTransformer.class, entityClass);

        @SuppressWarnings("unchecked")
        UniRowTransformer<T> tTransformer = (UniRowTransformer<T>) CACHED
                .computeIfAbsent(cacheKey, _k -> new UniReflectionRowTransformer<>(_k.getB()));
        return tTransformer;
    }


    /**
     * 创建一个通过反射将{@link RowSet}封装多个Bean，并返回{@link Multi}的转换器。
     *
     * @param entityClass 要封装的Bean类类型
     * @param <T> 要封装的Bean类型
     * @return 转换器
     * @see MultiRowTransformer
     * @see ReflectionRowTransformer
     * @see MultiReflectionRowTransformer
     */
    public static <T> MultiRowTransformer<T> multiRowForClass(Class<T> entityClass) {
        Tuple2<Class<?>, Class<?>> cacheKey = Tuples.of(MultiRowTransformer.class, entityClass);

        @SuppressWarnings("unchecked")
        MultiRowTransformer<T> tTransformer = (MultiRowTransformer<T>) CACHED
                .computeIfAbsent(cacheKey, _k -> new MultiReflectionRowTransformer<>(_k.getB()));
        return tTransformer;
    }


    /**
     * 创建一个将JSON字符串反序列化得到Bean的转换器。
     *
     * @param entityClass 要封装的Bean类类型
     * @param <T> 要封装的Bean类型
     * @return 转换器
     * @see JsonDeserializeTransformer
     * @see JacksonJsonDeserializeTransformer
     * @see SimpleJacksonJsonDeserializeTransformer
     */
    public static <T> JsonDeserializeTransformer<T> jsonDeserializeForClass(Class<T> entityClass) {
        Tuple2<Class<?>, Class<?>> cacheKey = Tuples.of(JsonDeserializeTransformer.class, entityClass);

        @SuppressWarnings("unchecked")
        SimpleJacksonJsonDeserializeTransformer<T> tTransformer = (SimpleJacksonJsonDeserializeTransformer<T>)
                CACHED.computeIfAbsent(cacheKey, _k -> new SimpleJacksonJsonDeserializeTransformer<>(_k.getB()));
        return tTransformer;
    }


    /**
     * 创建一个将JSON字符串反序列化得到Bean的转换器，并返回{@link Uni}的转换器。
     *
     * @param entityClass 要封装的Bean类类型
     * @param <T> 要封装的Bean类型
     * @return 转换器
     * @see UniJsonDeserializeTransformer
     * @see JacksonJsonDeserializeTransformer
     * @see UniJacksonJsonDeserializeTransformer
     */
    public static <T> UniJsonDeserializeTransformer<T> jsonDeserializeUniForClass(Class<T> entityClass) {
        Tuple2<Class<?>, Class<?>> cacheKey = Tuples.of(UniJsonDeserializeTransformer.class, entityClass);

        @SuppressWarnings("unchecked")
        UniJacksonJsonDeserializeTransformer<T> tTransformer = (UniJacksonJsonDeserializeTransformer<T>)
                CACHED.computeIfAbsent(cacheKey, _k -> new UniJacksonJsonDeserializeTransformer<>(_k.getB()));
        return tTransformer;
    }


    /**
     * 创建一个将JSON字符串反序列化得到Bean的转换器，并返回{@link Uni}的转换器。
     *
     * @param entityClass 要封装的Bean类类型
     * @param <T> 要封装的Bean类型
     * @return 转换器
     * @see MultiJsonDeserializeTransformer
     * @see JacksonJsonDeserializeTransformer
     * @see MultiJacksonJsonDeserializeTransformer
     */
    public static <T> MultiJsonDeserializeTransformer<T> jsonDeserializeMultiForClass(Class<T> entityClass) {
        Tuple2<Class<?>, Class<?>> cacheKey = Tuples.of(MultiJsonDeserializeTransformer.class, entityClass);

        @SuppressWarnings("unchecked")
        MultiJacksonJsonDeserializeTransformer<T, List<T>> tTransformer = (MultiJacksonJsonDeserializeTransformer<T, List<T>>)
                CACHED.computeIfAbsent(cacheKey, _k -> {
                    TypeReference<List<T>> typeRef = new TypeReference<>() {
                    };
                    return new MultiJacksonJsonDeserializeTransformer<>(typeRef);
                });
        return tTransformer;
    }



}
