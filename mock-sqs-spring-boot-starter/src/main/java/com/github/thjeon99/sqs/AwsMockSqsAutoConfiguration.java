package com.github.thjeon99.sqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.thjeon99.sqs.annotation.server.ConditionalOnMockSqs;
import com.github.thjeon99.sqs.config.SqsProperties;
import com.github.thjeon99.sqs.config.SqsQueues;

/**
 * Created by thjeon99@gmail.com on 2020. 10. 17.
 * 
 * Github : https://github.com/thjeon99
 */

@Slf4j
@Configuration
@ConditionalOnMockSqs
public class AwsMockSqsAutoConfiguration {

    @Bean
    @ConfigurationProperties("sqs")
    public SqsQueues sqsQueues() {
        return new SqsQueues();
    }

    @Bean
    @ConfigurationProperties("sqs.mock")
    public SqsProperties sqsProperties() {
        return new SqsProperties();
    }

}
