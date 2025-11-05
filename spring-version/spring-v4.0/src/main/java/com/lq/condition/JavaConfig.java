package com.lq.condition;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName JavaConfig
 * @Description
 * @Author liqiang
 * @Date 2025/11/5 17:34
 */
@Configuration
public class JavaConfig {

    @Bean
    @Conditional(MyCondition.class)
    public StudentService studentService() {
        return new StudentService();
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}
