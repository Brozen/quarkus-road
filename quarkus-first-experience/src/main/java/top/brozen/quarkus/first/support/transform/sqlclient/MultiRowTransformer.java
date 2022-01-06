package top.brozen.quarkus.first.support.transform.sqlclient;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import top.brozen.quarkus.first.support.transform.MultiTransformer;
import top.brozen.quarkus.first.support.transform.RowTransformer;

/**
 * 将数据行转换为{@link Multi}，适用于转换多行数据。
 *
 * @author Brozen
 * @since 2022-01-05
 */
public interface MultiRowTransformer<T> extends RowTransformer<Multi<T>>, MultiTransformer<RowSet<Row>, T> {

}
