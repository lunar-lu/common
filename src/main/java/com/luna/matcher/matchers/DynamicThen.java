package com.luna.matcher.matchers;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author jiaoteng
 */
public interface DynamicThen<T> {

    <K> FixMatchable<T, K> then(K result);

    <K> FixMatchable<T, K> then(Supplier<K> supplier);

    <K> FixMatchable<T, K> then(Function<T, K> fun);

//    <K> FixMatchable<T, K> then(Consumer<T> consumer);

}
