package com.lq.importBeanDefinitionRegistrar;

import com.lq.service.CacheService;
import com.lq.service.LoggerService;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName MyImportBeanDefinitionRegistrar
 * @Description `ImportBeanDefinitionRegistrar` 接口,也可以实现，相比 `ImportSelector` 接口的方式,ImportBeanDefinitionRegistrar
 *                  的方式是直接在定义的方法中提供了 `BeanDefinitionRegistry` ,自己在方法中实现注册。
 * @Author liqiang
 * @Date 2025/11/5 17:08
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition loggerBeanDefinition = new RootBeanDefinition(LoggerService.class);
        registry.registerBeanDefinition("loggerService",loggerBeanDefinition);
        RootBeanDefinition cacheBeanDefinition = new RootBeanDefinition(CacheService.class);
        registry.registerBeanDefinition("cacheService", cacheBeanDefinition);
    }
}
