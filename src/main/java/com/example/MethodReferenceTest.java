package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用
 */
@SuppressWarnings("all")
public class MethodReferenceTest {

    /**
     * 方法引用回溯
     */
    @Test
    public void test01() {
        UserBean userBean01 = new UserBean(1, "a");
        UserBean userBean02 = new UserBean(2, "b");
        UserBean userBean03 = new UserBean(3, "c");
        List<UserBean> userBeans = new ArrayList<>(Arrays.asList(userBean03, userBean02, userBean01));
        userBeans.sort(UserBean::compareById);    // 方法引用
        userBeans.stream().forEach(System.out::println);
    }

    /**
     * 静态方法引用
     *      Comparator：int compare(T o1, T o2)
     *      Integer：public static int compare(int x, int y)
     */
    @Test
    public void test02() {
        // 使用 Lambda
        Comparator<Integer> comparator1 = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator1.compare(1, 2));    // -1

        // 使用方法引用
        Comparator<Integer> comparator2 = Integer::compare;
        System.out.println(comparator2.compare(1, 2));    // -1
    }

    /**
     * 实例方法引用
     *      Consumer：void accept(T t)
     *      System.out：public void println(Object x)
     */
    @Test
    public void test03() {
        Consumer consumer1 = (msg) -> System.out.println(msg);
        consumer1.accept("Lambda 表达式");

        Consumer consumer2 = System.out::println;
        consumer2.accept("方法引用");
    }

    /**
     * 对象方法引用
     *      注意：只有 Lambda 的形式为：(x,y) -> x.method(y) 时，才能使用对象方法引用
     *      即：当左边的参数（例如本例："123"）是方法（例如本例："equal"）的调用者，右边参数（例如本例："456"）是方法的形参时，
     *
     *          比如：BiPredicate<String, String> biPredicate1 = (x, y) -> x.equals(y);
     *          简化：BiPredicate<String, String> biPredicate2 = String::equals;
     *
     *      BiPredicate：boolean test(T t, U u)
     *      String：public boolean equals(Object anObject)
     */
    @Test
    public void test04() {
        BiPredicate<String, String> biPredicate1 = (x, y) -> x.equals(y);
        System.out.println(biPredicate1.test("123", "456"));

        /**
         * 当左边的参数（例如本例："123"）是方法（例如本例："equal"）的调用者，右边参数（例如本例："456"）是方法的形参时，
         * 即："123".equals("456")，才能使用 "对象方法引用"，
         */
        BiPredicate<String, String> biPredicate2 = String::equals;
        System.out.println(biPredicate2.test("123", "456"));
    }

    /**
     * 构造方法引用
     */
    @Test
    public void test05() {
        Supplier<UserBean> userBeanSupplier1 = () -> new UserBean(101, "zhangsan");
        System.out.println(userBeanSupplier1.get());    // UserBean(id=101, name=zhangsan)

        // Supplier 的 get 方法为无参数，所以此处调用的是无参构造
        Supplier<UserBean> userBeanSupplier2 = UserBean::new;
        System.out.println(userBeanSupplier2.get());    // UserBean(id=null, name=null)
    }

}