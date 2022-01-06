package top.brozen.quarkus.first.support.transform.json;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import top.brozen.quarkus.first.support.transform.MultiTransformer;
import top.brozen.quarkus.first.support.transform.UniTransformer;

/**
 * @author Brozen
 * @since 2022-01-06
 */
public interface MultiJsonDeserializeTransformer<T>
        extends JsonDeserializeTransformer<Multi<T>>, MultiTransformer<String, T> {
}
