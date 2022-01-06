package top.brozen.quarkus.first.support.transform.json;

import top.brozen.quarkus.first.support.transform.Transformer;

/**
 * JSON字符串反序列化的转换器
 *
 * @author Brozen
 * @since 2022-01-06
 */
public interface JsonDeserializeTransformer<T> extends Transformer<String, T> {
}
