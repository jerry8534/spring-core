package com.hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("Hello Lombok");
        helloLombok.setAge(30);

        System.out.println("Name: " + helloLombok.getName());
        System.out.println("Age: " + helloLombok.getAge());
    }
}
