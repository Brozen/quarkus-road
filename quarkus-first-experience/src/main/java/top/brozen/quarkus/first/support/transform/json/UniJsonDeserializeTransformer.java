package top.brozen.quarkus.first.support.transform.json;

import io.smallrye.mutiny.Uni;
import top.brozen.quarkus.first.support.transform.UniTransformer;

/**
 * @author Brozen
 * @since 2022-01-06
 */
public interface UniJsonDeserializeTransformer<T>
        extends JsonDeserializeTransformer<Uni<T>>, UniTransformer<String, T> {
}
