package co.lunarlu.common.matchers;

import java.util.function.Predicate;

/**
 * @author jiaoteng
 */
public interface DynamicMatchable<T> extends Matchable<T> {

    /**
     * 对象 eq
     */
    DynamicThen<T> match(T that);

    /**
     * 条件匹配
     */
    DynamicThen<T> match(Predicate<T> predicate);

    /**
    * 类型匹配
    */
    DynamicThen<T> typeOf(Class<?> clazz);

}
