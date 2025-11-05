package com.lq.importBeanDefinitionRegistrar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName JavaConfig
 * @Description
 * @Author liqiang
 * @Date 2025/11/5 17:11
 */
@Configuration
@Import(MyImportBeanDefinitionRegistrar.class)
public class JavaConfig {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}
