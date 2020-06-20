package com.luna.matcher.matchers;

import java.util.function.Supplier;

/**
 * @author jiaoteng
 */
public interface BoolThen {

    <T> BoolOtherwise<T> then(T result);

    <T> BoolOtherwise<T> then(Supplier<T> supplier);

    BoolOtherwise.RunnableBoolOtherWise then(Runnable runnable);

}
