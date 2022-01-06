package top.brozen.quarkus.first.support.transform.sqlclient;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import top.brozen.quarkus.first.support.transform.RowTransformer;
import top.brozen.quarkus.first.support.transform.UniTransformer;

/**
 * 将数据行转换为{@link Uni}，适用于转换单行数据。
 *
 * @author Brozen
 * @since 2022-01-05
 */
public interface UniRowTransformer<T> extends RowTransformer<Uni<T>>, UniTransformer<RowSet<Row>, T> {
}
