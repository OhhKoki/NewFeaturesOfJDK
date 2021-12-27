package com.example;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda 语法形式：
 *
 *      1）(parameters) -> expression
 *      2）(parameters) -> { statements; }
 *
 *
 */
@SuppressWarnings("all")
public class LambdaTest {

    /**
     * 语法格式一：无参 & 无返回值（建议使用）
     */
    @Test
    public void test01 () {
        Runnable runnable = () -> System.out.println("语法格式一：无参 & 无返回值");
    }

    /**
     * 语法形式二：单参数 & 无返回值
     */
    @Test
    public void test02() {
        Consumer<String> consumer = (String msg) -> System.out.println(msg);
    }

    /**
     * 语法形式三：单参数 & 无返回值，参数类型可以省略（建议使用）
     */
    @Test
    public void test03() {
        Consumer<String> consumer = (msg) -> System.out.println(msg);
    }

    /**
     * 语法形式四：若 Lambda 为单参，参数的小括号也可以省略
     */
    @Test
    public void test04() {
        Consumer<String> consumer = msg -> System.out.println(msg);
    }

    /**
     * 语法形式五：若 Lambda 为多参，且需要执行多条语句 & 有返回值（建议使用）
     */
    @Test
    public void test05() {
        Comparator<Integer> comparator = (o1, o2) -> {
            System.out.println("两个 Integer 比较大小");
            return Integer.compare(o1, o2);
        };
    }

    /**
     * 语法形式六：若 Lambda 为多参，需要执行一条语句 & 有返回值 时，{} & return 都可以省略（建议使用）
     */
    @Test
    public void test06() {
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
    }

}
