package com.example;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class CreateOperation {

    /**
     * 创建 Stream 方式一
     *     在 Java8 中，对 Colletion 接口进行了扩展，提供了两个方法用于获取流：
     *          default Stream<E> stream()：获取一个串行流
     *          default Stream<E> parallelStream()：获取一个并行流
     */
    @Test
    public void test01() {
        List<String> list = new ArrayList<>();
        // 串行流
        Stream<String> stream = list.stream();
        // 并行流
        Stream<String> parallelStream = list.parallelStream();
    }

    /**
     * 创建 Stream 方式二
     */
    @Test
    public void test02() {
        Integer[] integers = new Integer[10];
        Stream<Integer> stream = Arrays.stream(integers);
    }

    /**
     * 创建 Stream 方式三
     */
    @Test
    public void test03() {
        // 返回包含给定元素的流
        Stream<Object> stream1 = Stream.of("tom", 1, true, 100.23);
        stream1.forEach(System.out::println);

        System.out.println("----------------------------------------");

        // 返回包含单个元素的流
        Stream<String> stream2 = Stream.of("terry");
        stream2.forEach(System.out::println);
    }

    /**
     * 创建 Stream 方式四：无限流
     */
    @Test
    public void test04() {
        /**
         * 创建无限流方式一：迭代
         *      seed --> 种子，即：操作的起始值
         *      当前案例：创建一个等差数列方法 Stream 中，且是无限循环（当前限制只要前 5 个）
         */
        Stream<Integer> stream1 = Stream.iterate(0, (x) -> x + 2);
        stream1.limit(5).forEach(System.out::println);

        /**
         * 创建无限流方式二：生成
         *		当前案例：创建随机放入 Stream 中，且是无限循环（当前限制只要前 5 个）
         */
        Stream<Double> stream2 = Stream.generate(() -> Math.random());
        stream2.limit(5).forEach(System.out::println);
    }

}
