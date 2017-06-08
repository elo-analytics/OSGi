package com.javaworld.sample.service.impl.HelloServiceImpl;

import com.javaworld.sample.helloservice.IHelloService;

public class HelloServiceImpl implements IHelloService{
    public String sayHello() {
        System.out.println("Inside HelloServiceImple.sayHello()");
        return "Say Hello";
    }
}