package com.lq.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName JavaConfig
 * @Description
 *    @Configuration 标识的Java类就是我们的配置类，相当于applicationContext.xml
 *    @Bean 修饰的就相当于 <bean><bean/>
 *    3.1版本提供 @ComponentScan()默认会扫描当前注解所修饰的java类所在的包及其子包下所有的 @Component注解修饰的Java类
 *    @ImportResource 注解的主要作用是：在基于Java配置的Spring应用中，导入一个或多个传统的XML格式的配置文件。
 *
 * @Author liqiang
 * @Date 2025/10/22 11:26
 */
@Configuration
@ComponentScan(basePackages = {"com.lq.service"})
@ImportResource("classpath:applicationContext.xml")
public class JavaConfig {


    public static void main(String[] args) {
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(JavaConfig.class);

        System.out.println(ac.getBean("userService"));

    }
}
