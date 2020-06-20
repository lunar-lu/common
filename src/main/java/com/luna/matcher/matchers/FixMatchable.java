package com.luna.matcher.matchers;

import java.util.function.Predicate;

/**
 * @author jiaoteng
 */
public interface FixMatchable<T, K> extends Matchable<T>, Resultable<K>, DefaultThen<T, K> {

    /**
     * 对象 eq
     */
    FixThen<T, K> match(T that);

    /**
     * 条件匹配
     */
    FixThen<T, K> match(Predicate<T> predicate);

    /**
     * 类型匹配
     */
    FixThen<T, K> typeOf(Class<T> clazz);

}
