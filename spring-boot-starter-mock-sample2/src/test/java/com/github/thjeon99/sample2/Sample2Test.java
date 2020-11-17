package com.github.thjeon99.sample2;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.thjeon99.sample2.SampleListener;

import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by thjeon99@gmail.com on 2020. 10. 23.
 * 
 * Github : https://github.com/thjeon99
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Sample2Test {

    @Autowired
    private SampleListener listener;

    @Autowired
    private QueueMessagingTemplate messagingTemplate;

    @Test
    public void default_listener_test() throws Exception {
        //given
        this.listener.setCountDownLatch(new CountDownLatch(1));

        //when
        messagingTemplate.convertAndSend("sample", "sample: "+ LocalDate.now().toString());

        //then
        assertTrue(this.listener.getCountDownLatch().await(15, TimeUnit.SECONDS));
    }
}
