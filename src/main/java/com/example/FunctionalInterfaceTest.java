package com.example;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置的四大核心函数式接口
 *
 *      1）消费型接口：interface Consumer<T>
 *
 *      2）供给型接口：interface Supplier<T>
 *
 *      3）函数型接口：interface Function<T, R>
 *
 *      4）断言型接口：interface Function<T, R>
 */
@SuppressWarnings("all")
public class FunctionalInterfaceTest {

    /**
     * 消费型接口：interface Consumer<T>
     *     void accept(T t);
     */
    @Test
    public void test01() {
        testConsumer("hello world", (msg) -> System.out.println(msg));
    }

    /**
     * 供给型接口：interface Supplier<T>
     *     T get();
     */
    @Test
    public void test02() {
        // 产生 10 个 100 以内的随机数
        List<Integer> list = testSUpplier(10, () -> (int) (Math.random() * 100));
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    /**
     * 函数型接口：interface Function<T, R>
     *     R apply(T t);
     */
    @Test
    public void test03() {
        String msg = testFunction("  hellod world", (str) -> str.trim());
        System.out.println(msg);
    }

    /**
     * 断言型接口：interface Function<T, R>
     *     R apply(T t);
     */
    @Test
    public void test04() {
        boolean b = testPredicate("hello world", (str) -> str.length() > 10);
        System.out.println(b);
    }

    public void testConsumer(Object obj, Consumer consumer) {
        consumer.accept(obj);
    }

    public List<Integer> testSUpplier(int num, Supplier<Integer> supplier) {
        List<Integer>list = new ArrayList<>();
        for (int i = 0; i < num; i ++) {
            list.add(supplier.get());
        }
        return list;
    }

    public String testFunction(String str, Function<String, String> function) {
        return function.apply(str);
    }

    public boolean testPredicate(String str, Predicate<String> predicate) {
        return predicate.test(str);
    }

}
