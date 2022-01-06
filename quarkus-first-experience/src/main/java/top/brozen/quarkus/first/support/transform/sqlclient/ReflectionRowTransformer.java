package top.brozen.quarkus.first.support.transform.sqlclient;

import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.SqlClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import top.brozen.quarkus.first.support.transform.RowTransformer;
import top.brozen.quarkus.first.support.transform.TransformMeta;

import java.lang.reflect.Field;

/**
 * 通过反射机制，将{@link SqlClient}的查询结果{@link Row}到特定Bean中。
 *
 * @author Brozen
 * @since 2022-01-05
 */
@Slf4j
public abstract class ReflectionRowTransformer<T, R> implements RowTransformer<R> {

    /**
     * Bean类型的反射信息。
     */
    private final TransformMeta<T> meta;


    public ReflectionRowTransformer(Class<T> clazz) {
        this.meta = TransformMeta.parse(clazz);
    }


    /**
     * 将查询结果的一行通过反射设置到Bean中。
     * @param rows 所有查询结果
     * @param row 要转换的行
     * @return 封装好的bean
     */
    protected T transformRow(RowSet<Row> rows, Row row) {
        T instance = createInstance();
        for (String columnName : rows.columnsNames()) {
            Field field = meta.fields().get(columnName);
            if (field == null) {
                continue;
            }

            try {
                if (!field.canAccess(instance) && !field.trySetAccessible()) {
                    log.warn("Access filed " + field.getName() + " of type "
                            + meta.clazz().getName() + " failed, field is inaccessible");
                    continue;
                }

                Object value = row.get(field.getType(), columnName);
                FieldUtils.writeField(field, instance, value);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Write field " + field.getName()
                        + " for type " + meta.clazz().getName() + " failed", e);
            }
        }

        return instance;
    }


    /**
     * 反射创建一个全新的Bean对象。
     */
    protected T createInstance() {
        T po;
        try {
            po = meta.constructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Create instance of type " + meta.clazz().getName() + " failed", e);
        }
        return po;
    }


}
