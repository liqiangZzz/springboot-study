package com.lq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName StartApp
 * @Description
 * @Author liqiang
 * @Date 2025/10/22 11:05
 */
public class StartApp {


    public static void main(String[] args) {
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(ac.getBean("userService"));
    }

}
