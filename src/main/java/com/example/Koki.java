package com.example;

import org.junit.Test;

/**
 * java8 接口新增默认方法与静态方法
 */
@SuppressWarnings("all")
public class Koki extends Dog implements Pet, Animal {

    @Test
    public void test01() {
        new Koki().call();
        Animal.sleep();
    }

}
