package top.brozen.quarkus.first.support.transform.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.limbo.utils.jackson.JacksonUtils;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 将JSON字符串反序列化为指定类型。
 *
 * @author Brozen
 * @since 2022-01-06
 * @param <D> 反序列化得到的类型
 * @param <T> 转换器最终返回的类型
 */
public abstract class JacksonJsonDeserializeTransformer<D, T> implements JsonDeserializeTransformer<T> {

    /**
     * 反序列化时使用的Jackson {@link ObjectMapper}
     */
    private final ObjectMapper objectMapper;

    /**
     * 反序列化得到的类型引用
     */
    private final TypeReference<D> typeRef;

    public JacksonJsonDeserializeTransformer(Class<D> type) {
        this(JacksonUtils.newObjectMapper(), type);
    }

    public JacksonJsonDeserializeTransformer(TypeReference<D> typeRef) {
        this(JacksonUtils.newObjectMapper(), typeRef);
    }

    public JacksonJsonDeserializeTransformer(ObjectMapper objectMapper, Class<D> type) {
        this(objectMapper, new TypeReference<>() {
            @Override
            public Type getType() {
                return type;
            }
        });
    }

    public JacksonJsonDeserializeTransformer(ObjectMapper objectMapper, TypeReference<D> typeRef) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
        this.typeRef = Objects.requireNonNull(typeRef);
    }


    /**
     * 将JSON字符串转换为指定Bean
     * @param json 源JSON字符串
     * @return 反序列化结果
     */
    public D transformJson(String json) {
        try {
            return objectMapper.readValue(json, typeRef);
        } catch (Exception e) {
            throw new IllegalStateException("Jackson deserialize failed for type " + typeRef.getType().getTypeName(), e);
        }
    }

}
