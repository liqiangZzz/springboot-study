package com.lq.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @ClassName MyCondition
 * @Description 。Condition是个接口，需要实现matches方法，返回true则注入bean，false则不注入。
 * @Author liqiang
 * @Date 2025/11/5 17:30
 */
public class MyCondition  implements Condition {
    /**
     * 判断是否匹配
     * @param conditionContext 条件上下文
     * @param annotatedTypeMetadata 注释类型元数据
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 默认为false
        return false;
    }
}
