package top.brozen.quarkus.first.support.sqlclient.transform;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.limbo.utils.tuple.Tuple2;
import org.limbo.utils.tuple.Tuples;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 便捷生成{@link Transformer}的工具类，内部有缓存。
 *
 * @author Brozen
 * @since 2022-01-05
 */
public class Transformers {


    static final ConcurrentHashMap<Tuple2<Class<?>, Class<?>>, Transformer<?>> CACHED = new ConcurrentHashMap<>();


    /**
     * 创建一个简单的通过反射封装单个Bean的转换器。
     *
     * @param entityClass 要封装的Bean类类型
     * @param <T> 要封装的Bean类型
     * @return 转换器
     * @see ReflectionTransformer
     * @see SimpleReflectionTransformer
     */
    public static <T> Transformer<T> forClass(Class<T> entityClass) {
        Tuple2<Class<?>, Class<?>> cacheKey = Tuples.of(Transformer.class, entityClass);

        @SuppressWarnings("unchecked")
        Transformer<T> tTransformer = (Transformer<T>) CACHED
                .computeIfAbsent(cacheKey, _k -> new SimpleReflectionTransformer<>(_k.getB()));
        return tTransformer;
    }


    /**
     * 创建一个通过反射封装单个Bean，并返回{@link Uni}的转换器。
     *
     * @param entityClass 要封装的Bean类类型
     * @param <T> 要封装的Bean类型
     * @return 转换器
     * @see UniTransformer
     * @see ReflectionTransformer
     * @see UniReflectionTransformer
     */
    public static <T> UniTransformer<T> forClassUni(Class<T> entityClass) {
        Tuple2<Class<?>, Class<?>> cacheKey = Tuples.of(UniTransformer.class, entityClass);

        @SuppressWarnings("unchecked")
        UniTransformer<T> tTransformer = (UniTransformer<T>) CACHED
                .computeIfAbsent(cacheKey, _k -> new UniReflectionTransformer<>(_k.getB()));
        return tTransformer;
    }


    /**
     * 创建一个通过反射封装多个Bean，并返回{@link Multi}的转换器。
     *
     * @param entityClass 要封装的Bean类类型
     * @param <T> 要封装的Bean类型
     * @return 转换器
     * @see MultiTransformer
     * @see ReflectionTransformer
     * @see MultiReflectionTransformer
     */
    public static <T> MultiTransformer<T> forClassMulti(Class<T> entityClass) {
        Tuple2<Class<?>, Class<?>> cacheKey = Tuples.of(MultiTransformer.class, entityClass);

        @SuppressWarnings("unchecked")
        MultiTransformer<T> tTransformer = (MultiTransformer<T>) CACHED
                .computeIfAbsent(cacheKey, _k -> new MultiReflectionTransformer<>(_k.getB()));
        return tTransformer;
    }



}
