package com.github.thjeon99.sqs.annotation.server;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * Created by thjeon99@gmail.com on 2020. 10. 17.
 * 
 * Github : https://github.com/thjeon99
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(OnMockSqsCondition.class)
public @interface ConditionalOnMockSqs {
}
