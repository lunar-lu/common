package com.luna.matcher.matchers;

import java.util.function.Supplier;

/**
 * @author jiaoteng
 */
public abstract class BoolMatcher<K> extends ResultMatcher<Boolean, K> implements BoolMatchable {
    protected BoolMatcher() {
    }

    public BoolMatcher(Boolean object, K result, boolean matched) {
        super(object, result, matched);
    }

    public static BoolMatcher<?> match(boolean bool) {
        return bool ? new TrueMatcher<>() : new FalseMatcher<>();
    }

    static class TrueMatcher<K> extends BoolMatcher<K> {

        @Override
        public <T> BoolOtherwise<T> then(T result) {
            return new MatchedOtherwise<>(result);
        }

        @Override
        public <T> BoolOtherwise<T> then(Supplier<T> supplier) {
            return new MatchedOtherwise<>(supplier.get());
        }

        @Override
        public BoolOtherwise.RunnableBoolOtherWise then(Runnable runnable) {
            runnable.run();
            return new MatchedRunnableOtherwise();
        }
    }

    static class FalseMatcher<K> extends BoolMatcher<K> {

        @Override
        public <T> BoolOtherwise<T> then(T result) {
            return new UnmatchedOtherwise<>();
        }

        @Override
        public <T> BoolOtherwise<T> then(Supplier<T> supplier) {
            return new UnmatchedOtherwise<>();
        }

        @Override
        public BoolOtherwise.RunnableBoolOtherWise then(Runnable runnable) {
            return new UnmatchedRunnableOtherwise();
        }
    }


    public static class UnmatchedOtherwise<T, K> extends ResultMatcher<T, K> implements BoolOtherwise<K> {

        @Override
        public Resultable<K> otherwise(K result) {
            return new MatchedOtherwise<>(result);
        }

        @Override
        public Resultable<K> otherwise(Supplier<K> supplier) {
            return new MatchedOtherwise<>(supplier.get());
        }
    }

    static class MatchedOtherwise<T, K> extends ResultMatcher<T, K> implements BoolOtherwise<K> {

        public MatchedOtherwise(K result) {
            super(result);
        }

        @Override
        public Resultable<K> otherwise(K result) {
            return this;
        }

        @Override
        public Resultable<K> otherwise(Supplier<K> supplier) {
            return this;
        }
    }


    public static class UnmatchedRunnableOtherwise implements BoolOtherwise.RunnableBoolOtherWise {

        @Override
        public void otherwise(Runnable runnable) {
            runnable.run();
        }
    }


    public static class MatchedRunnableOtherwise implements BoolOtherwise.RunnableBoolOtherWise {

        @Override
        public void otherwise(Runnable runnable) {

        }
    }


}
