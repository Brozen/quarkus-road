package top.brozen.quarkus.first.support.sqlclient.transform;

import io.smallrye.mutiny.Multi;

/**
 * 将数据行转换为{@link Multi}，适用于转换多行数据。
 *
 * @author Brozen
 * @since 2022-01-05
 */
public interface MultiTransformer<T> extends Transformer<Multi<T>> {

}
