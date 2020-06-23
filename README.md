
#### java pattern matching

- Boolean match

```java

        Matchers.match(true)
                .then(() -> {
                    assert true;
                })
                .otherwise(() -> {
                    assert false;
                });

        int value = Matchers.match(false)
                .then(() -> 1)
                .otherwise(2)
                .result();
        assert value == 2;
```

- any match

```java
        Integer case = Matchers.of("11")
                .match("22").then(1)
                .match("11").then(() -> null)
                .match("11").then(3)
                .defaultThen(4)
                .result();
        assert case == 4;

        Integer case2 = Matchers.of("11")
                .match("22").then(1)
                .result();
        assert Objects.isNull(case2);
```

