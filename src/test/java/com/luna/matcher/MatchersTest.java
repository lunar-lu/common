package com.luna.matcher;

import org.junit.Test;

import java.util.Objects;


/**
 * @author jiaoteng
 */
public class MatchersTest {

    @Test
    public void matchTest() {
        Matchers.match(true)
                .then(() -> {
                    assert true;
                })
                .otherwise(() -> {
                    assert false;
                });

        Matchers.match(false)
                .then(() -> {
                    assert false;
                })
                .otherwise(() -> {
                    assert true;
                });

        int value = Matchers.match(true)
                .then(1)
                .otherwise(() -> 2)
                .result();
        assert value == 1;

        value = Matchers.match(false)
                .then(() -> 1)
                .otherwise(2)
                .result();
        assert value == 2;
    }

    @Test
    public void ofTest() {


        int case1 = Matchers.of("123")
                .match("asd").then(() -> 1)
                .match("123").then(() -> 2)
                .match("123").then(() -> 10000)
                .result();
        assert case1 == 2;

        int case2 = Matchers.of("123")
                .match("456"::equals).then(1)
                .match("123"::equals).then(2)
                .match("789"::equals).then(3)
                .result();
        assert case2 == 2;

        int case3 = Matchers.of(1)
                .match(0).then(v -> v + 0)
                .match(1).then(v -> v + 1)
                .match(2).then(v -> v + 2)
                .result();
        assert case3 == 2;

        int case4 = Matchers.of("123")
                .match("asd").then(() -> 1)
                .match("123").then(() -> 2)
                .result();
        assert case4 == 2;

        String case5 = Matchers.of("123")
                .match("asd").then(() -> 10)
                .match("abc").then(() -> 20)
                .defaultThen(0)
                .map(v -> v + "10")
                .result();
        assert case5.equals("010");

        String case6 = Matchers.of("123")
                .match("asd").then("1")
                .defaultThen("123456")
                .result();
        assert case6.equals("123456");

        try {
            Integer a = Matchers.of("11")
                    .match("1").then(1)
                    .resultOptional().orElseThrow(() -> new NullPointerException(""));
            assert false;
            assert Objects.isNull(a);
        } catch (NullPointerException e) {
            assert true;
        }


        Integer case7 = Matchers.of("11")
                .match("22").then(1)
                .match("11").then(() -> null)
                .match("11").then(3)
                .defaultThen(4)
                .result();
        assert case7 == 4;


        Integer case8 = Matchers.of("11")
                .match("22").then(1)
                .result();
        assert Objects.isNull(case8);
    }
}