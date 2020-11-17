package com.github.thjeon99.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.github.thjeon99.sqs.annotation.server.ConditionalOnMockSqs;
import com.github.thjeon99.sqs.annotation.server.ConditionalOnMockSqsServer;
import com.github.thjeon99.sqs.config.SqsProperties;
import com.github.thjeon99.sqs.config.SqsQueues;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.github.thjeon99.sqs.MockQueueGenerator.generate;
import static com.github.thjeon99.sqs.MockQueueGenerator.getSqsClient;

import org.elasticmq.rest.sqs.SQSRestServer;
import org.elasticmq.rest.sqs.SQSRestServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

/**
 * Created by thjeon99@gmail.com on 2020. 10. 17.
 * 
 * Github : https://github.com/thjeon99
 */

@Slf4j
@RequiredArgsConstructor
@Configuration
@ConditionalOnMockSqs
public class AwsMockSqsServerAutoConfiguration {
    private final SqsProperties sqsProperties;
    private final SqsQueues sqsQueues;


    @Bean
    @Primary
    @DependsOn("sqsRestServer")
    @ConditionalOnMockSqsServer
    public AmazonSQSAsync amazonSqs() {
        return getSqsClient(sqsProperties.getEndPoint());
    }

    @Bean(destroyMethod="stopAndWait")
    @Primary
    @ConditionalOnMockSqsServer
    public SQSRestServer sqsRestServer() {
        SQSRestServer sqsServer = createMockSqsServer();

        generate(sqsProperties.isEnabled(), sqsProperties.getEndPoint(), sqsQueues);

        return sqsServer;
    }

    private SQSRestServer createMockSqsServer() {
        return SQSRestServerBuilder
                .withInterface(sqsProperties.getHost())
                .withPort(sqsProperties.getPort())
                .start();
    }

}
