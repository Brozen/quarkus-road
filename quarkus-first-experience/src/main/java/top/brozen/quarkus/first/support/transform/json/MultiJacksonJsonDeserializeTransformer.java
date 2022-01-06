package top.brozen.quarkus.first.support.transform.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Multi;

/**
 * 将JSON字符串反序列化为指定集合类型，并以{@link Multi}形式返回。
 *
 * @author Brozen
 * @since 2022-01-06
 * @param <T> 集合类型中，每个元素的具体类型
 * @param <I> 集合类型
 */
public class MultiJacksonJsonDeserializeTransformer<T, I extends Iterable<T>>
        extends JacksonJsonDeserializeTransformer<I, Multi<T>>
        implements MultiJsonDeserializeTransformer<T> {

    public MultiJacksonJsonDeserializeTransformer(TypeReference<I> typeRef) {
        super(typeRef);
    }

    public MultiJacksonJsonDeserializeTransformer(ObjectMapper objectMapper, TypeReference<I> typeRef) {
        super(objectMapper, typeRef);
    }


    /**
     * {@inheritDoc}
     * @param source 源数据
     * @return
     */
    @Override
    public Multi<T> transform(String source) {
        return Multi.createFrom().iterable(transformJson(source));
    }

}
