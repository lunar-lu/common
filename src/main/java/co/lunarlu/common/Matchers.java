package co.lunarlu.common;

import co.lunarlu.common.matchers.BoolMatcher;
import co.lunarlu.common.matchers.DynamicMatchable;
import co.lunarlu.common.matchers.BoolMatchable;
import co.lunarlu.common.matchers.DynamicMatcher;

/**
 * @author jiaoteng
 */
public class Matchers {

    public static <T> DynamicMatchable<T> of(T object) {
        return DynamicMatcher.of(object);
    }

    public static BoolMatchable match(boolean bool) {
        return BoolMatcher.match(bool);
    }

}
