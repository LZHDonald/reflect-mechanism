package com.lzh.model;

/**
 * @author lzh
 * create 2019-10-27-10:36
 */
public class Dog {

    private String name;
    private Integer age;

    public String type;
    public Integer height;

    private Dog() {
    }

    private Dog(String name, Integer height) {
        this.name = name;
        this.height = height;
    }

    public Dog(String name, String type, Integer height, Integer age) {
        this.name = name;
        this.type = type;
        this.height = height;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                ", height=" + height +
                '}';
    }
}
