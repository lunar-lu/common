package com.luna.matcher;

import com.luna.matcher.matchers.BoolMatchable;
import com.luna.matcher.matchers.BoolMatcher;
import com.luna.matcher.matchers.DynamicMatchable;
import com.luna.matcher.matchers.DynamicMatcher;

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
