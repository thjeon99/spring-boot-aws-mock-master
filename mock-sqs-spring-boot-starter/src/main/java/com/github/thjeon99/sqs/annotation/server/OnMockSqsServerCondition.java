package com.github.thjeon99.sqs.annotation.server;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by thjeon99@gmail.com on 2020. 10. 17.
 * 
 * Github : https://github.com/thjeon99
 */

/**
 * 실행중인 Mock SQS 서버가 없을 경우
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 40)
class OnMockSqsServerCondition extends OnMockSqsServerBaseCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        boolean isRunning = isRunning(context);
        return new ConditionOutcome(!isRunning, createMessage(isRunning));
    }
}
