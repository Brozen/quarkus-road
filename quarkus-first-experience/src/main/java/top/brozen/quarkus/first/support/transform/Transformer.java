package top.brozen.quarkus.first.support.transform;

import java.util.function.Function;

/**
 * @author Brozen
 * @since 2022-01-06
 */
public interface Transformer<S, T> extends Function<S, T> {


    /**
     * 将源数据转换为另一个数据
     * @param source 源数据
     * @return 转换结果
     */
    T transform(S source);


    /**
     * {@link Function}本身的函数会被代理到{@link #transform(Object)}方法，因为{@link #transform(Object)}方法更具有语义化。
     */
    @Override
    default T apply(S source) {
        return transform(source);
    }

}
