package top.brozen.quarkus.first.support.transform;

import io.smallrye.mutiny.Uni;

/**
 * 将源数据转换为指定类型，并返回{@link Uni}的转换器。
 *
 * @author Brozen
 * @since 2022-01-06
 */
public interface UniTransformer<S, T> extends Transformer<S, Uni<T>> {
}
