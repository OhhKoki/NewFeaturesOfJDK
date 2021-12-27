package com.example;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream 中间操作
 */
@SuppressWarnings("all")
public class IntermediateOperation {

    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 3333.33),
            new Employee("里斯", 58, 5555.55),
            new Employee("王武", 26, 3333.33),
            new Employee("赵流", 36, 6666.66),
            new Employee("田七", 37, 8888.88),
            new Employee("田七", 37, 8888.88)
    );

    /**
     * Stream<T> filter(Predicate<? super T> predicate)：过滤
     */
    @Test
    public void test01() {
        employees.stream()
                 // 过滤年龄小于 35 的员工
                 .filter(((employee) -> employee.getAge() > 35))
                 .forEach(System.out::println);
    }

    /**
     * Stream<T> distinct()：去重
     */
    @Test
    public void test02() {
        employees.stream()
                // 去除重复的元素，元素是否重新，根据元素的 hashcode() & equals() 方法来判断
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * Stream<T> limit(long maxSize)：截断流，使流的元素不超过指定个数
     */
    @Test
    public void test03() {
        employees.stream()
                 // 截断流从而得到一个新的流，新流的元素个数不超过指定值
                 .limit(2)
                 .forEach(System.out::println);
    }

    /**
     * Stream<T> skip(long n)：去掉当前 Stream 的前 n 个元素，并返回一个新的流
     */
    @Test
    public void test04() {
        employees.stream()
                 // 跳过旧流的前 n 个元素从而得到一个新流，与 limit 刚好互补
                 .skip(2)
                 .forEach(System.out::println);
    }

    /**
     * <R> Stream<R> map(Function<? super T, ? extends R> mapper)：
     *      接收一个 Function，然后将该 Function 应用在旧流的每个元素上，并将返回的值收集到一个新流中
     */
    @Test
    public void test05() {
        employees.stream()
                 // 将 Lambda 应用在旧流的每个元素上，并将得到的新元素收集到新流中
                 .map((employee) -> employee.getSalary() + 1000 )
                 .forEach(System.out::println);

        System.out.println("------------------------------");

        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream()
            .map((str) -> str.toUpperCase())
            .forEach(System.out::println);
    }

    /**
     * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
     *      接收一个 Function，然后将流中的每个元素都转成一个新流，然后将所有流合并成一个流
     */
    @Test
    public void test06() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");

        list.stream()
            .map(IntermediateOperation::filterCharacter)    // 效果：{{a,a,a}, {b,b,b}, {c,c,c}, {d,d,d}}
            .forEach((arr) -> arr.forEach(System.out::println));

        System.out.println("------------------------------");

        list.stream()
            .flatMap(IntermediateOperation::filterCharacter)    // 效果：{a,a,a,b,b,b,c,c,c,d,d,d}
            .forEach(System.out::println);
    }

    /**
     * 测试 map 跟 flatMap 的区别
     *      有点跟 Colltion 中的 add 跟 addAll 方法类似：
     *          add：无论是元素还是集合，整体加到其中一个集合中去 [1,2,3,[2,3]]
     *          addAl：无论是元素还是集合，都是将元素加到另一个集合中去 [1,2,3,2,3]
     * @param str
     * @return
     */
    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

    /**
     * Comparable
     *      Stream<T> sorted()：自然排序，使用 Comparable 进行比较
     */
    @Test
    public void test07() {
        List<String> list = Arrays.asList("dd", "cc", "bb", "aa");
        list.stream()
            .sorted()
            .forEach(System.out::println);
    }

    /**
     * Comparator
     *      int compare(T o1, T o2)：定制排序
     */
    @Test
    public void test08() {
        employees.stream()
                 .sorted((x, y) -> {
                     if (x.getAge() == y.getAge()) {
                         return Double.compare(x.getSalary(), y.getSalary());
                     }else {
                         return Integer.compare(x.getAge(), y.getAge());
                     }
                 })
                .forEach(System.out::println);
    }

}