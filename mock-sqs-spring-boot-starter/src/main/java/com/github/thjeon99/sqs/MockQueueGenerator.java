package com.github.thjeon99.sqs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.github.thjeon99.sqs.annotation.server.MockServerMessageType;
import com.github.thjeon99.sqs.config.SqsQueues;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by thjeon99@gmail.com on 24/11/2019
 * 
 * Github : http://github.com/thjeon99
 */
@Slf4j
@RequiredArgsConstructor
public class MockQueueGenerator {

    public static void generate(boolean isMockServerEnabled, String sqsEndPoint, SqsQueues queues) {
        AmazonSQSAsync sqsClient = getSqsClient(sqsEndPoint);

        if(isMockServerEnabled) {
            log.info(MockServerMessageType.CREATE_SERVER.getMessage());
            queues.getQueues().forEach(queue -> sqsClient.createQueue(queue.createQueueRequest()));
        } else {
            log.info(MockServerMessageType.USE_SERVER.getMessage());
        }

    }

    public static AmazonSQSAsync getSqsClient(String sqsEndPoint) {
        return AmazonSQSAsyncClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
                    .withEndpointConfiguration(new EndpointConfiguration(sqsEndPoint, ""))
                    .build();
    }
}
