package com.lq.redisson;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RedissonAutoConfiguration
 * @Description redisson 自动装配类
 * @Author liqiang
 * @Date 2025/11/7 16:49
 */
@ConditionalOnClass(Redisson.class) // 条件注解
@EnableConfigurationProperties(RedissonProperties.class)
@Configuration
public class RedissonAutoConfiguration {

    /**
     * RedissonClient:操作Redis的核心对象
     * 将该对象注入到容器中
     * 需要连接Redis 服务， host  port
     *
     * @return
     */
    @Bean
    public RedissonClient redissonClient(RedissonProperties redissonProperties) {
        Config config = new Config();
        String prefix = "redis://";
        if (redissonProperties.isSsl()) {
            prefix = "rediss://";
        }
        config.useSingleServer()
                .setAddress(prefix + redissonProperties.getHost() + ":" + redissonProperties.getPort())
                .setTimeout(redissonProperties.getTimeout());
        return Redisson.create(config);
    }
}
