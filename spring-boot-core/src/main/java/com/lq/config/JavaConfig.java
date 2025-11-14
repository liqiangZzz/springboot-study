package com.lq.config;

import com.lq.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName JavaConfig
 * @Description
 * @Author liqiang
 * @Date 2025/11/7 17:46
 */

@Configuration
public class JavaConfig {

    @Bean
    public ProductService productService() {
        return new ProductService();
    }
}
