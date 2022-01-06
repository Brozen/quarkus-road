package top.brozen.quarkus.first.support.transform.sqlclient;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 将数据行转换为{@link Multi}，适用于转换多行数据。每一行数据都通过反射的方式生成。
 *
 * @author Brozen
 * @since 2022-01-05
 */
public class MultiReflectionRowTransformer<T> extends ReflectionRowTransformer<T, Multi<T>> implements MultiRowTransformer<T> {


    public MultiReflectionRowTransformer(Class<T> clazz) {
        super(clazz);
    }

    /**
     * {@inheritDoc}
     * @param rows 查询结果
     * @return
     */
    @Override
    public Multi<T> transform(RowSet<Row> rows) {
        List<T> instances = new ArrayList<>();

        for (Row row : rows) {
            instances.add(transformRow(rows, row));
        }

        return Multi.createFrom().iterable(instances);
    }


}
