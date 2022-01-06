package top.brozen.quarkus.first.support.transform.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

/**
 * 将JSON字符串反序列化为指定类型，并以{@link Multi}形式返回。
 *
 * @author Brozen
 * @since 2022-01-06
 * @param <T> 反序列化得到的类型
 */
public class UniJacksonJsonDeserializeTransformer<T>
        extends JacksonJsonDeserializeTransformer<T, Uni<T>>
        implements UniJsonDeserializeTransformer<T> {

    public UniJacksonJsonDeserializeTransformer(Class<T> type) {
        super(type);
    }

    public UniJacksonJsonDeserializeTransformer(TypeReference<T> typeRef) {
        super(typeRef);
    }

    public UniJacksonJsonDeserializeTransformer(ObjectMapper objectMapper, Class<T> type) {
        super(objectMapper, type);
    }

    public UniJacksonJsonDeserializeTransformer(ObjectMapper objectMapper, TypeReference<T> typeRef) {
        super(objectMapper, typeRef);
    }


    /**
     * {@inheritDoc}
     * @param source 源数据
     * @return
     */
    @Override
    public Uni<T> transform(String source) {
        return Uni.createFrom().item(transformJson(source));
    }

}
