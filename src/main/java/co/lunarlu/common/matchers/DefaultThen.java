package co.lunarlu.common.matchers;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author jiaoteng
 */
public interface DefaultThen<T, K> {

    Resultable<K> defaultThen(K result);

    Resultable<K> defaultThen(Supplier<K> supplier);

    Resultable<K> defaultThen(Function<T, K> fun);

    void defaultThen(Runnable runnable);
}
