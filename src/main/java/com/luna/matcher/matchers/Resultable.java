package com.luna.matcher.matchers;


import java.util.Optional;
import java.util.function.Function;

public interface Resultable<K> {

    public K result();

    Optional<K> resultOptional();

    <U> Resultable<U> map(Function<K, U> mapper);
}