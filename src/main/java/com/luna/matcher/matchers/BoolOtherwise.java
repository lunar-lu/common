package com.luna.matcher.matchers;

import java.util.function.Supplier;

/**
 * @author jiaoteng
 */
public interface BoolOtherwise<K> {

    Resultable<K> otherwise(K result);

    Resultable<K> otherwise(Supplier<K> supplier);

    interface RunnableBoolOtherWise {

        void otherwise(Runnable runnable);
    }
}
