package co.lunarlu.common.matchers;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author jiaoteng
 */
public interface FixThen<T, K> {

    FixMatchable<T, K> then(K result);

    FixMatchable<T, K> then(Supplier<K> supplier);

    FixMatchable<T, K> then(Function<T, K> fun);

    FixMatchable<T, K> then(Consumer<T> consumer);

    FixMatchable<T, K> then(Runnable runnable);

}
