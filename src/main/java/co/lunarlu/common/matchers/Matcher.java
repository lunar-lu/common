package co.lunarlu.common.matchers;

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
