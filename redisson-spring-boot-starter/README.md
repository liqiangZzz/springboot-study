# Redisson Spring Boot Starter

## 模块介绍

redisson-spring-boot-starter 是一个用于简化 Redisson 在 Spring Boot 项目中集成的自动化配置模块。该模块提供了对 Redisson 客户端的自动配置功能，使得开发者可以快速地在 Spring Boot 应用中使用 Redisson 提供的分布式锁、分布式集合等高级特性。

## 功能特性

- 自动配置 Redisson 客户端
- 支持 SSL 连接配置
- 可配置的连接超时时间
- 简化的 Redis 配置属性绑定

## 使用方法

### 1. 添加依赖

在您的 Spring Boot 项目中添加以下 Maven 依赖：

```xml
<dependency>
    <groupId>com.lq</groupId>
    <artifactId>redisson-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### 2. 配置文件设置

在 `application.yml` 或 `application.properties` 中添加 Redisson 相关配置：

```yaml
lq.redisson:
  host: localhost
  port: 6379
  timeout: 1000
  ssl: false
```

### 3. 使用 RedissonClient

在您的服务类中直接注入 RedissonClient：

```java
@Service
public class YourService {
    
    @Autowired
    private RedissonClient redissonClient;
    
    public void yourMethod() {
        // 使用 RedissonClient 进行操作
        RLock lock = redissonClient.getLock("your-lock-key");
        // ... 其他操作
    }
}
```

## 核心组件

### RedissonAutoConfiguration

自动配置类，负责创建和初始化 RedissonClient 实例。

### RedissonProperties

配置属性类，用于绑定配置文件中的 Redisson 相关属性。

### additional-spring-configuration-metadata.json

配置元数据文件，用于提供配置属性的描述信息和默认值，使IDE能够提供更好的配置提示功能。

```json
{
  "properties": [{
    "name": "lq.redisson.host",
    "type": "java.lang.String",
    "description": "Redis的服务地址",
    "defaultValue": "localhost"
  },{
    "name": "lq.redisson.port",
    "type": "java.lang.Integer",
    "description": "Redis的服务端口",
    "defaultValue": 6379
  }]
}
```

### spring.factories

Spring Boot自动配置注册文件，用于注册自动配置类，使RedissonAutoConfiguration能够在Spring Boot启动时自动加载。

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.lq.redisson.RedissonAutoConfiguration
```

## 依赖关系

- `org.redisson:redisson:3.15.6` - Redisson 核心库
- `org.springframework.boot:spring-boot-starter:2.4.8` - Spring Boot 启动器
- `org.springframework.boot:spring-boot-configuration-processor:2.4.8` - 配置处理器

## 配置选项

| 属性 | 默认值 | 描述 |
|------|--------|------|
| lq.redisson.host | localhost | Redis 服务器地址 |
| lq.redisson.port | 6379 | Redis 服务器端口 |
| lq.redisson.timeout | 1000 | 连接超时时间（毫秒） |
| lq.redisson.ssl | false | 是否启用 SSL 连接 |

## 注意事项

1. 确保 Redis 服务器已正确安装并运行
2. 根据实际环境调整 Redis 连接配置
3. 在生产环境中建议启用 SSL 连接以提高安全性