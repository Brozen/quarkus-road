package top.brozen.quarkus.first.support.sqlclient.transform;

import io.smallrye.mutiny.Uni;

/**
 * 将数据行转换为{@link Uni}，适用于转换单行数据。
 *
 * @author Brozen
 * @since 2022-01-05
 */
public interface UniTransformer<T> extends Transformer<Uni<T>> {
}
