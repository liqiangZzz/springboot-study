# Spring Boot Core 模块

## 模块概述

Spring Boot Core 是一个核心配置与业务服务模块，作为提供方为满足特定条件的外部应用提供服务组件。该模块通过条件化自动配置机制，实现了基于运行时条件的Bean按需注册。

## 核心特性

- 作为提供方提供条件化的服务组件
- 实现基于条件注解的自动配置机制
- 支持运行时条件判断和Bean的按需注册
- 提供编译期优化与运行时灵活性的平衡

## 架构组件

### ConditionalConfig 条件化配置类

``java
@Configuration
@ConditionalOnClass(name = "com.lq.autoconfigure.CoreAutoConfiguration")
public class ConditionalConfig {
    @Bean
    @ConditionalOnBean(name = "primaryProductService")
    public ProductService conditionalProductService() {
        System.out.println("提供方创建的conditionalProductService Bean");
        return new ProductService();
    }
}
```

作为提供方，该配置类为满足特定前置条件的外部应用提供增强功能：

**条件化注解说明：**
- [@ConditionalOnClass](src/main/java/com/lq/config/ConditionalConfig.java#L30): 类路径条件注解，确保配置类仅在指定类存在于 classpath 时加载
- [@ConditionalOnBean](src/main/java/com/lq/config/ConditionalConfig.java#L57): Bean存在条件注解，确保Bean仅在指定Bean已注册时创建

**Bean命名策略：**
- `primaryProductService`: 调用方定义的基础服务Bean
- `conditionalProductService`: 满足条件时由本配置类创建的增强服务Bean
- 通过不同的Bean名称避免类型冲突问题

**同名Bean行为说明：**
如果提供方和调用方使用相同的Bean名称，后注册的Bean会覆盖先注册的Bean。具体哪个生效取决于注册顺序，这可能导致不可预测的行为。例如：

``java
// 调用方定义
@Bean("productService")
public ProductService productService() {
    System.out.println("调用方的ProductService");
    return new ProductService();
}

// 提供方定义（后注册）
@Bean("productService")  // 相同名称
public ProductService productService() {
    System.out.println("提供方的ProductService");
    return new ProductService();
}
```

在这种情况下，最终容器中只会有一个名为"productService"的Bean，即提供方定义的Bean，因为它后注册而覆盖了调用方的Bean。

为避免这种情况，建议：
1. 使用具有明确语义的Bean名称
2. 提供方和调用方使用不同的Bean名称
3. 在开发和测试阶段注意Bean覆盖的警告信息

```

### ProductService 服务类

``java
public class ProductService {
}
```

这是一个基础的服务类，当前为空实现，可以在此基础上扩展产品相关的业务逻辑。该类遵循标准的 POJO 设计模式，便于在 Spring 容器中进行管理。

作为提供方，该类为满足条件的外部应用提供基础的产品服务功能。

### spring-autoconfigure-metadata.properties 自动配置元数据

自动配置元数据文件，用于定义自动配置类的条件化加载规则。该文件与配置类中的条件注解协同工作，提供更高效的自动配置机制。

``properties
com.lq.config.ConditionalConfig.ConditionalOnClass=com.lq.autoconfigure.CoreAutoConfiguration
com.lq.config.ConditionalConfig.conditionalProductService.ConditionalOnBean=primaryProductService
```

协同工作机制：
- [ConditionalConfig](src/main/java/com/lq/config/ConditionalConfig.java) 类在编译期通过元数据文件进行候选者筛选
- 在运行期通过注解进行实际的条件判断和Bean注册
- 两者结合实现了编译期优化和运行时灵活性的平衡

## 集成指南

### 1. Maven 依赖配置

在需要使用该模块的项目中添加 Maven 依赖：

``xml
<dependency>
    <groupId>com.lq</groupId>
    <artifactId>spring-boot-core</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### 2. 配置类集成

作为提供方，本模块通过条件化配置为外部应用提供服务，使用时需满足特定前置条件：

**条件化使用方式：**
当外部应用满足以下前置条件时，可自动激活配置：

1. classpath中存在`com.lq.autoconfigure.CoreAutoConfiguration`类
2. Spring容器中已注册名为"primaryProductService"的Bean

```java
// 满足条件后，通过组件扫描自动发现配置类
@ComponentScan(basePackages = "com.lq")
public class Application {
}
```

或者显式导入配置类：

```java
@Configuration
@Import(ConditionalConfig.class)
public class ApplicationConfiguration {
}
```

**前置条件配置示例：**

调用方需要提供以下配置以满足条件：

```
// 1. 创建CoreAutoConfiguration类以满足@ConditionalOnClass条件
package com.lq.autoconfigure;

public class CoreAutoConfiguration {
    // 可以为空实现，仅用于触发条件注解
}

// 2. 创建primaryProductService Bean以满足@ConditionalOnBean条件
@Configuration
public class YourConfiguration {
    @Bean("primaryProductService")
    public ProductService primaryProductService() {
        return new ProductService();
    }
}
```

### 3. 服务注入使用

在需要使用 ProductService 的类中注入：

``java
@Service
public class BusinessService {
    
    // 基础服务注入
    @Autowired
    @Qualifier("primaryProductService")
    private ProductService primaryProductService;
    
    // 条件化服务注入（仅在满足条件时可用）
    @Autowired(required = false)
    @Qualifier("conditionalProductService")
    private ProductService conditionalProductService;
    
    // 使用服务进行业务处理
    public void performBusinessLogic() {
        // 使用基础服务
        primaryProductService.performOperation();
        
        // 使用条件化服务（如果可用）
        if (conditionalProductService != null) {
            conditionalProductService.performOperation();
        }
    }
}
```

或者通过ApplicationContext直接获取特定Bean：

``java
@SpringBootApplication
@Import(ConditionalConfig.class)
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        
        // 正确的获取方式：通过Bean名称获取特定Bean
        ProductService primaryService = context.getBean("primaryProductService", ProductService.class);
        
        // 获取条件化服务（如果存在）
        if (context.containsBean("conditionalProductService")) {
            ProductService conditionalService = context.getBean("conditionalProductService", ProductService.class);
        }
        
        // 错误的获取方式：会导致NoUniqueBeanDefinitionException
        // ProductService service = context.getBean(ProductService.class);
    }
}
```

**注意：** 如果您在main方法中直接使用`context.getBean(ProductService.class)`而没有指定Bean名称，将会遇到NoUniqueBeanDefinitionException异常，因为Spring容器中存在多个ProductService类型的Bean。请使用以下方式之一解决：

1. 通过Bean名称获取特定Bean：
   ```java
   ProductService service = context.getBean("primaryProductService", ProductService.class);
   ```

2. 使用@Primary注解标记首选Bean（在调用方配置中）：
   ```java
   @Bean("primaryProductService")
   @Primary
   public ProductService primaryProductService() {
       return new ProductService();
   }
   ```

## 技术依赖

- `org.springframework:spring-context:5.2.16.RELEASE` - Spring 上下文核心库
- `org.springframework.boot:spring-boot-autoconfigure:2.4.8` - Spring Boot 自动配置支持

## 扩展性设计

如需扩展功能，可以：

1. 在 [ProductService](src/main/java/com/lq/service/ProductService.java) 中添加具体的业务方法
2. 在 [ConditionalConfig](src/main/java/com/lq/config/ConditionalConfig.java) 中定义更多的条件化Bean
3. 添加其他服务组件和配置
4. 调整条件注解实现更复杂的自动配置逻辑
5. 添加新的条件化配置类以提供不同类型的服务组件
6. 为不同的Bean使用不同的类型，避免类型冲突
7. 设计继承关系明确的Bean类型体系，通过类型而非名称区分不同功能的Bean

## 最佳实践建议

1. 确保满足条件化配置的前置条件
2. 合理使用条件化注解优化自动配置性能
3. 通过自动配置元数据文件提升启动效率
4. 使用不同的Bean名称避免类型冲突
5. 使用@Qualifier注解明确指定要注入的Bean
6. 对于条件化Bean，使用@Autowired(required = false)处理可选性
7. 避免提供方和调用方使用相同的Bean名称，确保Bean标识的唯一性

## 注意事项

1. 在生产环境中使用条件化注解时，需要确保条件判断的准确性，避免因条件不满足导致预期的Bean未注册
2. 自动配置元数据文件需要与实际的条件注解保持同步，避免出现不一致的情况
3. 条件化配置会增加应用启动时的条件判断开销，需要在灵活性和性能之间找到平衡点
4. 当存在多个相同类型的Bean时，必须使用@Qualifier注解或通过Bean名称获取特定Bean，避免NoUniqueBeanDefinitionException异常
5. 避免提供方和调用方使用相同的Bean名称，后注册的Bean会覆盖先注册的Bean，可能导致不可预测的行为
