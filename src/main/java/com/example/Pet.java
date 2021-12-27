package com.example;

@SuppressWarnings("all")
public interface Pet {

    default void call() {
        System.out.println("宠物叫");
    }

}
