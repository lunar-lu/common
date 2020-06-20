package com.luna.matcher.matchers;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author jiaoteng
 */
public interface FixThen<T, K> {

    FixMatchable<T, K> then(K result);

    FixMatchable<T, K> then(Supplier<K> supplier);

    FixMatchable<T, K> then(Function<T, K> fun);

}
