package top.brozen.quarkus.first.support.transform.sqlclient;

import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

/**
 * 将查询结果行通过反射封装到Bean中，只转换为一个Bean对象，如果返回了多行，只处理第一行。
 *
 * @author Brozen
 * @since 2022-01-05
 */
public class SimpleReflectionRowTransformer<T> extends ReflectionRowTransformer<T, T> {


    public SimpleReflectionRowTransformer(Class<T> clazz) {
        super(clazz);
    }


    /**
     * {@inheritDoc}
     * @param rows 查询结果
     * @return
     */
    @Override
    public T transform(RowSet<Row> rows) {
        Row row = rows.iterator().next();
        if (row == null) {
            return null;
        }

        return transformRow(rows, row);
    }


}
