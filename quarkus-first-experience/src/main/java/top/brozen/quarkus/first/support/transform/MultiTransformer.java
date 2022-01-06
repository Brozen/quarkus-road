package top.brozen.quarkus.first.support.transform;

import io.smallrye.mutiny.Multi;

/**
 * 将源数据转换为指定类型，并返回{@link Multi}的转换器。
 *
 * @author Brozen
 * @since 2022-01-06
 */
public interface MultiTransformer<S, T> extends Transformer<S, Multi<T>> {
}
