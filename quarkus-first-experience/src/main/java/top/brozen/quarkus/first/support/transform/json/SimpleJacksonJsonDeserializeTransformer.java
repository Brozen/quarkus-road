package top.brozen.quarkus.first.support.transform.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 将JSON字符串反序列化为指定类型并返回。
 *
 * @author Brozen
 * @since 2022-01-06
 */
public class SimpleJacksonJsonDeserializeTransformer<T> extends JacksonJsonDeserializeTransformer<T, T> {

    public SimpleJacksonJsonDeserializeTransformer(Class<T> type) {
        super(type);
    }

    public SimpleJacksonJsonDeserializeTransformer(TypeReference<T> typeRef) {
        super(typeRef);
    }

    public SimpleJacksonJsonDeserializeTransformer(ObjectMapper objectMapper, Class<T> type) {
        super(objectMapper, type);
    }

    public SimpleJacksonJsonDeserializeTransformer(ObjectMapper objectMapper, TypeReference<T> typeRef) {
        super(objectMapper, typeRef);
    }


    /**
     * {@inheritDoc}
     * @param source 源数据
     * @return
     */
    @Override
    public T transform(String source) {
        return null;
    }
}
