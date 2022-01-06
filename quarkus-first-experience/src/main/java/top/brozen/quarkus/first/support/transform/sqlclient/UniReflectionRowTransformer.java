package top.brozen.quarkus.first.support.transform.sqlclient;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.SqlClient;

/**
 * 通过反射机制，将{@link SqlClient}的查询结果{@link Row}到特定Bean中，通过反射方式生成Bean。
 * 如果RowSet中存在多行数据，只会转换第一行。
 *
 * @author Brozen
 * @since 2022-01-05
 */
public class UniReflectionRowTransformer<T> extends ReflectionRowTransformer<T, Uni<T>> implements UniRowTransformer<T> {


    public UniReflectionRowTransformer(Class<T> clazz) {
        super(clazz);
    }


    /**
     * {@inheritDoc}
     * @param rows 查询结果
     * @return
     */
    @Override
    public Uni<T> transform(RowSet<Row> rows) {
        Row row = rows.iterator().next();
        if (row == null) {
            return Uni.createFrom().nullItem();
        }

        return Uni.createFrom().item(transformRow(rows, row));
    }


}
