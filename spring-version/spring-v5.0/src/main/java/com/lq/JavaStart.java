package com.lq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName JavaStart
 * @Description
 * @Author liqiang
 * @Date 2025/11/5 17:41
 */
@Configuration
@ComponentScan("com.lq")
public class JavaStart {

    public static void main(String[] args) {
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(JavaStart.class);
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}
