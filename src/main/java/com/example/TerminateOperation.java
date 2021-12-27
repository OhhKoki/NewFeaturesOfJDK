package com.example;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream 终止操作
 */
@SuppressWarnings("all")
public class TerminateOperation {

    private List<Emp> emps = Arrays.asList(
            new Emp("张三", 18, 3333.33, Emp.Status.FREE),
            new Emp("里斯", 58, 5555.55, Emp.Status.BUSY),
            new Emp("王武", 26, 3333.33, Emp.Status.VOCHATION),
            new Emp("赵流", 36, 6666.66, Emp.Status.FREE),
            new Emp("田七", 37, 8888.88, Emp.Status.BUSY)
    );

    /**
     * boolean allMatch(Predicate<? super T> predicate)：Stream 中的所有元素都能匹配 Lambda
     */
    @Test
    public void test01() {
        boolean match = emps.stream()
                // Stream 中所有元素的状态都为 BUSY
                .allMatch((emp) -> emp.getStatus().equals(Emp.Status.BUSY));
        System.out.println(match);
    }

    /**
     * boolean anyMatch(Predicate<? super T> predicate)：Stream 中至少存在一个元素能匹配 Lambda
     */
    @Test
    public void test02() {
        boolean match = emps.stream()
                // Stream 中存在元素的状态为 BUSY
                .anyMatch((emp) -> emp.getStatus().equals(Emp.Status.BUSY));
        System.out.println(match);
    }

    /**
     * boolean noneMatch(Predicate<? super T> predicate)：Stream 中没有元素能匹配 Lambda
     */
    @Test
    public void test03() {
        boolean match = emps.stream()
                // Stream 中不存在元素的状态为 BUSY
                .noneMatch((emp) -> emp.getStatus().equals(Emp.Status.BUSY));
        System.out.println(match);
    }

    /**
     * Optional<T> findFirst()：从 Stream 中获取第一个元素
     */
    @Test
    public void test04() {
        Optional<Emp> first = emps.stream()
                // 按工资进行排序
                .sorted((x, y) -> Double.compare(x.getSalary(), y.getSalary()))
                // 返回工资最低的员工信息
                .findFirst();
        System.out.println(first.get());
    }

    /**
     * Optional<T> findAny()：随机返回 Stream 中的一个元素
     */
    @Test
    public void tset05() {
        Optional<Emp> any = emps.stream()
                // 刷选空闲的员工
                .filter((emp) -> emp.getStatus().equals(Emp.Status.FREE))
                // 随便返回一个
                .findAny();
        System.out.println(any.get());
    }

    /**
     * long count()：返回 Stream 中元素总数
     */
    @Test
    public void test06() {
        long count = emps.stream()
                .count();
        System.out.println(count);
    }

    /**
     * Optional<T> max(Comparator<? super T> comparator)：根据 Lambda 的比较规则，返回流中的最大值
     */
    @Test
    public void test07() {
        Optional<Emp> max = emps.stream()
                .max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
        System.out.println(max.get());
    }

    /**
     * Optional<T> min(Comparator<? super T> comparator)：根据 Lambda 的比较规则，返回流中的最小值
     */
    @Test
    public void test08() {
        Optional<Double> min = emps.stream()
                /**
                 * 对象方法引用
                 *      只有 Lambda 的形式为：(x,y) -> x.method(y) 时，才能使用对象方法引用，
                 *      此时右边的参数为空，所以能对上，相当于：(x,空) ->x.getSalary(空)
                 */
                .map(Emp::getSalary)
                /**
                 * 静态方法引用
                 *      Comparator：int compare(T o1, T o2)
                 *      Integer：public static int compare(int x, int y)
                 */
                .min(Double::compare);
        System.out.println(min.get());
    }

    /**
     * T reduce(T identity, BinaryOperator<T> accumulator)：归约，有起始值
     *      可以将流中的元素反复结合起来，得到一个值，返回 T
     *      identity：起始值（起始值只有第一次有作用，之后每次计算，都是将上一次的结果作为下一次计算的起始值）
     */
    @Test
    public void tset09() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer reduce = list.stream()
                /**
                 * 第一次运算：(0,1) = 1
                 * 第二次运算：(1,2) = 3
                 * 第三次运算：(3,3) = 6
                 * 第四次运算：(6,4) = 6
                 * ...
                 */
                .reduce(0, Integer::sum);
        System.out.println(reduce);
    }

    /**
     * Optional<T> reduce(BinaryOperator<T> accumulator)：归约，无起始值
     *      可以将流中的元素反复结合起来，得到一个值，返回 Optional（因为没有起始值，所以结果可能为空，Optional 就是用于处理 NVL）
     */
    public void test10() {
        // 计算工资总和
        Optional<Double> salarySum = emps.stream()
                /**
                 * map 和 reduce 的连接通常称为 map-reduce 模式：
                 *      map：用于提取数据
                 *      reduce：用于结合
                 */
                .map(Emp::getSalary)
                .reduce(Double::sum);
        System.out.println(salarySum.get());
    }

    /**
     * Collector 接口中方法的实现决定了如何对流执行收集操作（收集哪个集合：List/Set/Map）
     * Collectors 类提供了很多静态方法，通过这些静态方法，我们可以方便的额创建常见的收集器实例，例如：
     *      toList：ArrayList
     *      toSet：HashSet
     *      toMap：HashMap
     */
    @Test
    public void test11() {
        List<String> arrayList = emps.stream()
                .map(Emp::getName)
                .collect(Collectors.toList());
        arrayList.forEach(System.out::println);

        System.out.println("-------------------------");

        Set<String> hashSet = emps.stream()
                .map(Emp::getName)
                .collect(Collectors.toSet());
        hashSet.forEach(System.out::println);

        System.out.println("-------------------------");

        LinkedList<String> linkedList = emps.stream()
                .map(Emp::getName)
                .collect(Collectors.toCollection(LinkedList::new));
        linkedList.forEach(System.out::println);
    }

    /**
     * Collector<T, ?, DoubleSummaryStatistics> summarizingDouble(ToDoubleFunction<? super T> mapper)
     *      对 Stream 中的所有元素（必须都是 double 类型）进行统计，从而得到统计值。例如：
     *          public final long getCount()：获取元素个数
     *          public final double getMax()：获取元素最大值
     *          public final double getMin()：获取元素最小值
     *          public final double getSum()：获取元素总值
     *          public final double getAverage()：获取元素平均值
     *
     * Collector<T, ?, IntSummaryStatistics> summarizingInt(ToIntFunction<? super T> mapper)
     *      对 Stream 中的所有元素（必须都是 int 类型）进行统计，从而得到统计值。例如：
     *          public final long getCount()：获取元素个数
     *          public final int getMax()：获取元素最大值
     *          public final int getMin()：获取元素最小值
     *          public final int getSum()：获取元素总值
     *          public final int getAverage()：获取元素平均值
     *
     * Collector<T, ?, LongSummaryStatistics> summarizingLong(ToLongFunction<? super T> mapper)
     *      对 Stream 中的所有元素（必须都是 long 类型）进行统计，从而得到统计值。例如：
     *          public final long getCount()：获取元素个数
     *          public final long getMax()：获取元素最大值
     *          public final long getMin()：获取元素最小值
     *          public final long getSum()：获取元素总值
     *          public final long getAverage()：获取元素平均值
     */
    @Test
    public void test12() {
        // double
        DoubleSummaryStatistics dss = emps.stream()
                .collect(Collectors.summarizingDouble(Emp::getSalary));
        System.out.println(dss.getCount());
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getSum());
        System.out.println(dss.getAverage());

        System.out.println("------------------------------");

        // Integer
        IntSummaryStatistics iss = emps.stream()
                .collect(Collectors.summarizingInt(Emp::getAge));
        System.out.println(iss.getCount());
        System.out.println(iss.getMax());
        System.out.println(iss.getMin());
        System.out.println(iss.getSum());
        System.out.println(iss.getAverage());
    }

    /**
     * groupingBy(Function<? super T, ? extends K> classifier)
     *      根据属性值对 Stream 就行分组，分组结果为 Map，属性值为 key，分组结果（List）为 value
     */
    @Test
    public void test13() {
        Map<Emp.Status, List<Emp>> map = emps.stream()
//                .collect(Collectors.groupingBy((emp) -> emp.getStatus()))
                // 对象方法引用，此处相当于：(emp,空) -> emp.getStatus(空)，所以能用对象方法引用
                .collect(Collectors.groupingBy(Emp::getStatus));
        System.out.println(map);
    }

    /**
     * 将 Stream 中的元素分成两个区，满足 Lambda 元素分在 true 区，不满足的在 false 区
     *      {false=[元素,元素,元素], true=[元素,元素,元素]}
     */
    @Test
    public void test14() {
        Map<Boolean, List<Emp>> map = emps.stream()
                .collect(Collectors.partitioningBy((emp) -> emp.getStatus().equals(Emp.Status.BUSY)));
        System.out.println(map);
    }

}
