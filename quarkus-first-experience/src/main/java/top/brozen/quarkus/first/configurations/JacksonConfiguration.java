package top.brozen.quarkus.first.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.jackson.ObjectMapperCustomizer;
import org.limbo.utils.jackson.JacksonUtils;

import javax.enterprise.inject.Instance;
import javax.inject.Singleton;

/**
 * @author Brozen
 * @since 2022-01-04
 */
@Singleton
public class JacksonConfiguration {


    /**
     * 需要接受{@link ObjectMapperCustomizer}并手动处理自定义的{@link ObjectMapper}，
     * 否则CDI框架不会自动应用这些customizer，可能导致Quarkus框架本身的一些处理异常
     */
    @Singleton
    ObjectMapper objectMapper(Instance<ObjectMapperCustomizer> customizers) {
        ObjectMapper mapper = JacksonUtils.newObjectMapper();

        // Apply all ObjectMapperCustomizer beans (incl. Quarkus)
        for (ObjectMapperCustomizer customizer : customizers) {
            customizer.customize(mapper);
        }

        return mapper;
    }

}
