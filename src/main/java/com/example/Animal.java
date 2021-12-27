package com.example;

@SuppressWarnings("all")
public interface Animal {

    default void call() {
        System.out.println("动物叫");
    }

    static void sleep() {
        System.out.println("动物睡");
    }

}
