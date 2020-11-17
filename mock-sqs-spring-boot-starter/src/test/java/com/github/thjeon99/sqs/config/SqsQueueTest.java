package com.github.thjeon99.sqs.config;

import org.junit.Test;

import com.github.thjeon99.sqs.config.SqsQueues;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by thjeon99@gmail.com on 2020. 10. 19.
 * 
 * Github : https://github.com/thjeon99
 */

public class SqsQueueTest {

    @Test
    public void SqsQueue_default_attribute() {
        SqsQueues.SqsQueue queueData = new SqsQueues.SqsQueue();

        assertThat(queueData.getDefaultVisibilityTimeout()).isEqualTo(3L);
        assertThat(queueData.getDelay()).isEqualTo(0L);
        assertThat(queueData.getReceiveMessageWait()).isEqualTo(0L);
    }
}
