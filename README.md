# Spring Boot 学习项目

## 项目介绍

这是一个用于学习 Spring 及 Spring Boot 源码的多模块 Maven 项目。项目包含了 Spring 框架从 v1.0 到 v5.0 的演进示例、SPI 机制实现、Redisson 集成等多个模块，旨在帮助开发者深入理解 Spring 框架的核心原理和使用方法。

## 项目结构

```
springboot-study/
├── redisson-spring-boot-starter/    # Redisson Spring Boot Starter 封装
├── spi/                             # SPI 机制实现不同数据库数据源扩展
│   ├── my-data-base/                # 公共接口定义
│   ├── mysql-data-base/             # MySQL 实现
│   ├── oracle-data-base/            # Oracle 实现
│   └── spring-boot-demo/            # SPI 演示应用
├── spring-boot-core/                # 核心配置与业务服务
├── spring-version/                  # Spring 各版本演进示例
│   ├── spring-v1.0/                 # Spring 1.x 版本示例
│   ├── spring-v2.0/                 # Spring 2.x 版本示例
│   ├── spring-v3.0/                 # Spring 3.x 版本示例
│   ├── spring-v4.0/                 # Spring 4.x 版本示例
│   └── spring-v5.0/                 # Spring 5.x 版本示例
```

## 模块详细介绍

### Redisson Spring Boot Starter 模块

封装了 Redisson 的 Spring Boot Starter，提供了 Redisson 客户端的自动配置功能，简化了在 Spring Boot 项目中使用 Redisson 分布式锁等特性的集成过程。

### SPI 模块

演示了 Java SPI（Service Provider Interface）机制的使用方法，通过定义公共接口和多种数据库实现，展示了如何实现服务的动态扩展和插件化架构。

### Spring Boot Core 模块

核心配置与业务服务模块，展示了 Spring 框架的基本配置和组件管理功能，使用 Java 配置方式定义和管理 Spring Bean。

### Spring Version 模块

包含从 Spring v1.0 到 v5.0 的不同版本示例，展示了 Spring 框架在不同版本中的特性和使用方式的变化，帮助理解 Spring 框架的演进过程。

## 技术栈

- Java 8
- Spring Framework 1.2.9 - 5.2.16.RELEASE
- Spring Boot 2.4.8
- Redisson 3.15.6
- Maven 3.x

## 环境要求

- JDK 8 或更高版本
- Maven 3.x
- Redis 服务器（用于 Redisson 模块）

## 使用方法

### 1. 克隆项目

```bash
git clone <项目地址>
```

### 2. 编译项目

```bash
mvn clean install
```

### 3. 运行特定模块

进入相应的模块目录，执行：

```bash
mvn exec:java
```

或者

```bash
mvn spring-boot:run
```

## 学习建议

1. 按模块顺序学习，理解每个模块的功能和实现原理
2. 对比不同版本 Spring 的配置方式差异
3. 实践 SPI 机制的扩展性特性
4. 掌握 Redisson 在分布式系统中的应用

## 注意事项

1. 各模块间可能存在依赖关系，请按顺序编译
2. 部分模块需要特定的运行环境（如 Redis）
3. 示例代码主要用于学习目的，生产环境使用需进一步完善