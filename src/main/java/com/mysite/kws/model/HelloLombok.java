package com.mysite.kws.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class HelloLombok {
    private String name;
    private int age;
    private final String test = "test";

    public HelloLombok(String test, int i) {

    }

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok("test", 1);
        System.out.println(helloLombok.getName());
        System.out.println(helloLombok.getAge());
        System.out.println(helloLombok.getTest());
    }
}
