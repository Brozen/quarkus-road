package top.brozen.quarkus.first.support.transform;

import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.SqlClient;

import java.util.function.Function;

/**
 * 用于将{@link SqlClient}返回的响应式结果RowSet转换为其他类型的转换器。
 * 该接口继承了{@link Function}，便于在Mutiny等响应式框架中使用。
 *
 * @author Brozen
 * @since 2022-01-05
 * @param <T> 需要转换至的类型，可以转换任何类型。
 */
public interface RowTransformer<T> extends Transformer<RowSet<Row>, T> {

    /**
     * 将{@link RowSet}转换为指定类型
     * @param rows 查询结果
     * @return 转换结果
     */
    T transform(RowSet<Row> rows);

}
