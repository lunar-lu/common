package com.luna.matcher.matchers;

import com.luna.matcher.Matchers;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author jiaoteng
 */
public abstract class ResultMatcher<T, K> extends Matcher<T> implements Resultable<K> {

    protected K result;

    private boolean matched;

    protected ResultMatcher(K result) {
        super();
        this.result = result;
        this.matched = true;
    }


    protected ResultMatcher(T object, K result) {
        super(object);
        this.result = result;
        matched = true;
    }

    protected ResultMatcher(T object, K result, boolean matched) {
        super(object);
        this.result = result;
        this.matched = matched;
    }

    protected ResultMatcher() {
        super();
    }

    protected boolean isUnMatched() {
        return !isMatched();
    }

    protected boolean isMatched() {
        return this.matched;
    }


    public void ifMatched(Consumer<K> consumer) {
        if (isMatched()) {
            consumer.accept(result);
        }
    }

    public Optional<K> resultOptional() {
        return Optional.ofNullable(this.result);
    }


    @Override
    public K result() {
        return this.result;
    }

    @Override
    public <U> Resultable<U> map(Function<K, U> mapper) {
        return Matchers.match(isMatched())
                .<Resultable<U>>then(
                        new ResultMatcher<T, U>(this.object, mapper.apply(result), true) {
                        }
                )
                .otherwise(
                        new ResultMatcher<T, U>(this.object, null, false) {
                        }
                )
                .result();
    }
}
