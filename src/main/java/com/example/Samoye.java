package com.example;

import org.junit.Test;

public class Samoye implements Pet, Animal{

    @Override
    public void call() {
        Pet.super.call();
    }

    @Test
    public void test01() {
        Animal.sleep();
    }

}
