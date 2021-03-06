package co.lunarlu.common.matchers;

import co.lunarlu.common.Matchers;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author jiaoteng
 */
public class DynamicMatcher<T, K> extends ResultMatcher<T, K> implements DynamicMatchable<T> {

    public DynamicMatcher(T object, K result) {
        super(object, result);
    }

    public static <T, K> DynamicMatcher<T, K> of(T object) {
        return new DynamicMatcher<>(object, null);
    }

    @Override
    public DynamicThen<T> match(T that) {
        return match(o -> Objects.equals(this.object, that));
    }

    @Override
    public DynamicThen<T> match(Predicate<T> predicate) {
        return Matchers.match(predicate.test(this.object))
                .<DynamicThen<T>>then(new MatchedDynamicThen<>(this.object))
                .otherwise(new UnmatchedDynamicThen<>(this.object))
                .result();
    }

    @Override
    public DynamicThen<T> typeOf(Class<?> clazz) {
        return match(o ->
                Objects.nonNull(clazz) &&
                        Objects.nonNull(o) &&
                        clazz.isAssignableFrom(o.getClass())
        );
    }

    static class MatchedDynamicThen<T> extends Matcher<T> implements DynamicThen<T> {

        public MatchedDynamicThen(T object) {
            super(object);
        }

        private <K> FixMatchable<T, K> fixMatchable(K result) {
            return new FixMatcher.MatchedFixMatcher<>(this.object, result, true);

        }

        @Override
        public <K> FixMatchable<T, K> then(K result) {
            return fixMatchable(result);
        }

        @Override
        public <K> FixMatchable<T, K> then(Supplier<K> supplier) {
            return fixMatchable(supplier.get());
        }

        @Override
        public <K> FixMatchable<T, K> then(Function<T, K> fun) {
            return fixMatchable(fun.apply(this.object));
        }

        @Override
        public FixMatchable<T, Void> then(Consumer<T> consumer) {
            consumer.accept(this.object);
            return fixMatchable(null);
        }

        @Override
        public FixMatchable<T, Void> then(Runnable runnable) {
            runnable.run();
            return fixMatchable(null);
        }

    }


    static class UnmatchedDynamicThen<T> extends Matcher<T> implements DynamicThen<T> {

        public UnmatchedDynamicThen(T object) {
            super(object);
        }

        private <K> FixMatchable<T, K> fixMatchable() {
            return new FixMatcher<>(this.object, null, false);
        }

        @Override
        public <K> FixMatchable<T, K> then(K result) {
            return fixMatchable();
        }

        @Override
        public <K> FixMatchable<T, K> then(Supplier<K> supplier) {
            return fixMatchable();
        }

        @Override
        public <K> FixMatchable<T, K> then(Function<T, K> fun) {
            return fixMatchable();
        }

        @Override
        public FixMatchable<T, Void> then(Consumer<T> consumer) {
            return fixMatchable();
        }

        @Override
        public FixMatchable<T, Void> then(Runnable runnable) {
            return fixMatchable();
        }

    }

}
