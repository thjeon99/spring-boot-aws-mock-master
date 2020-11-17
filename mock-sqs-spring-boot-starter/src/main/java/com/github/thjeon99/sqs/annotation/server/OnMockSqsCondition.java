package com.github.thjeon99.sqs.annotation.server;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by thjeon99@gmail.com on 2020. 10. 17.
 * 
 * Github : https://github.com/thjeon99
 */

@Order(Ordered.HIGHEST_PRECEDENCE + 40)
class OnMockSqsCondition extends SpringBootCondition {

    private static final String MOCK_ENABLED = "sqs.mock.enabled";

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String mockEnabled = context.getEnvironment().getProperty(MOCK_ENABLED);
        boolean match = "true".equals(mockEnabled);
        return new ConditionOutcome(match, createMessage(match));
    }

    private String createMessage(boolean match){
        return match? "Execute Mock Sqs" : "Execute AWS SQS";
    }
}
