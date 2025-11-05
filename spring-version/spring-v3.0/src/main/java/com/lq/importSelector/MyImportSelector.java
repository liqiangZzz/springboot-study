package com.lq.importSelector;

import com.lq.service.CacheService;
import com.lq.service.LoggerService;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

/**
 * @ClassName MyImportSelector
 * @Description
 * @Author liqiang
 * @Date 2025/11/5 17:02
 */
public class MyImportSelector implements ImportSelector {

    /**
     * 选择需要导入的类名数组
     * @param annotationMetadata 注解元数据
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{LoggerService.class.getName(), CacheService.class.getName()};
    }

    /**
     * 获取排除过滤器
     * @return  排除过滤器谓词，用于过滤不需要导入的字符串
     */
    @Override
    public Predicate<String> getExclusionFilter() {
        return ImportSelector.super.getExclusionFilter();
    }
}
