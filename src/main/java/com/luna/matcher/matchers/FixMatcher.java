package com.luna.matcher.matchers;

import com.luna.matcher.Matchers;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author jiaoteng
 */
public class FixMatcher<T, K> extends ResultMatcher<T, K> implements FixMatchable<T, K> {

    public FixMatcher(T object, K result, boolean matched) {
        super(object, result, matched);
    }

    @Override
    public FixThen<T, K> match(T that) {
        return match(o -> Objects.equals(o, that));
    }

    @Override
    public FixThen<T, K> match(Predicate<T> predicate) {
        return Matchers.match(predicate.test(this.object))
                .<FixThen<T, K>>then(new MatchedFixThen<>(this.object))
                .otherwise(new UnMatchedFixThen<>(this))
                .result();
    }

    @Override
    public FixThen<T, K> typeOf(Class<T> clazz) {
        return match(o ->
                Objects.nonNull(clazz) &&
                        Objects.nonNull(o) &&
                        clazz.isAssignableFrom(o.getClass()));
    }

    private Resultable<K> resultable(K result) {
        return new MatchedFixMatcher<>(this.object, result, true);
    }

    @Override
    public Resultable<K> defaultThen(K result) {
        return Matchers.match(isUnMatched())
                .then(resultable(result))
                .otherwise(this)
                .result();
    }

    @Override
    public Resultable<K> defaultThen(Supplier<K> supplier) {
        return Matchers.match(isUnMatched())
                .then(resultable(supplier.get()))
                .otherwise(this)
                .result();
    }

    @Override
    public Resultable<K> defaultThen(Function<T, K> fun) {
        return Matchers.match(isUnMatched())
                .then(resultable(fun.apply(this.object)))
                .otherwise(this)
                .result();
    }

    @Override
    public void defaultThen(Runnable runnable) {
        Matchers.match(isUnMatched())
                .then(runnable)
                .otherwise(() -> {
                })
        ;
    }


    public static class MatchedFixThen<T, K> extends Matcher<T> implements FixThen<T, K> {

        public MatchedFixThen(T object) {
            super(object);
        }

        private FixMatcher<T, K> fixMatcher(K result) {
            return new MatchedFixMatcher<>(this.object, result, true);
        }

        @Override
        public FixMatcher<T, K> then(K result) {
            return fixMatcher(result);
        }

        @Override
        public FixMatcher<T, K> then(Supplier<K> supplier) {
            return fixMatcher(supplier.get());

        }

        @Override
        public FixMatcher<T, K> then(Function<T, K> fun) {
            return fixMatcher(fun.apply(this.object));
        }

        @Override
        public FixMatchable<T, K> then(Consumer<T> consumer) {
            consumer.accept(this.object);
            return fixMatcher(null);
        }

        @Override
        public FixMatchable<T, K> then(Runnable runnable) {
            runnable.run();
            return fixMatcher(null);
        }
    }

    public static class UnMatchedFixThen<T, K> implements FixThen<T, K> {

        private final FixMatcher<T, K> fixMatcher;

        public UnMatchedFixThen(FixMatcher<T, K> fixMatcher) {
            this.fixMatcher = fixMatcher;
        }

        @Override
        public FixMatcher<T, K> then(K result) {
            return this.fixMatcher;
        }

        @Override
        public FixMatcher<T, K> then(Supplier<K> supplier) {
            return this.fixMatcher;
        }

        @Override
        public FixMatcher<T, K> then(Function<T, K> fun) {
            return this.fixMatcher;
        }

        @Override
        public FixMatchable<T, K> then(Consumer<T> consumer) {
            return this.fixMatcher;
        }

        @Override
        public FixMatchable<T, K> then(Runnable runnable) {
            return this.fixMatcher;
        }

    }

    static class MatchedFixMatcher<T, K> extends FixMatcher<T, K> {

        public MatchedFixMatcher(T object, K result, boolean matched) {
            super(object, result, matched);
        }

        public FixThen<T, K> fixThen(FixMatchable<T, K> matchable) {

            return new FixThen<T, K>() {
                @Override
                public FixMatchable<T, K> then(K result) {
                    return matchable;
                }

                @Override
                public FixMatchable<T, K> then(Supplier<K> supplier) {
                    return matchable;
                }

                @Override
                public FixMatchable<T, K> then(Function<T, K> fun) {
                    return matchable;
                }

                @Override
                public FixMatchable<T, K> then(Consumer<T> consumer) {
                    return matchable;
                }

                @Override
                public FixMatchable<T, K> then(Runnable runnable) {
                    return matchable;
                }
            };

        }

        @Override
        public FixThen<T, K> match(T that) {
            return fixThen(this);
        }

        @Override
        public FixThen<T, K> match(Predicate<T> predicate) {
            return fixThen(this);
        }

        @Override
        public FixThen<T, K> typeOf(Class<T> clazz) {
            return fixThen(this);
        }
    }
}
