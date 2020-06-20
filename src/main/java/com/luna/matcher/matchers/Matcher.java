package com.luna.matcher.matchers;

/**
 * @author jiaoteng
 */

public class Matcher<T> implements Matchable<T> {

    protected T object;

    public Matcher() {
    }

    public Matcher(T object) {
        this.object = object;
    }
}
