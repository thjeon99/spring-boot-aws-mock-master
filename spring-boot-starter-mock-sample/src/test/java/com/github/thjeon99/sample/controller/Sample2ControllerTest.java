package com.github.thjeon99.sample.controller;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.thjeon99.sample.domain.Point;
import com.github.thjeon99.sample.domain.PointRepository;
import com.github.thjeon99.sample.dto.PointDto;
import com.github.thjeon99.sample.listener.Sample2Listener;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by thjeon99@gmail.com on 2020. 9. 19.
 * 
 * Github : https://github.com/thjeon99
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Sample2ControllerTest {

    @Autowired
    Sample2Listener sample2Listener;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    private QueueMessagingTemplate messagingTemplate;

    @After
    public void tearDown() throws Exception {
        pointRepository.deleteAllInBatch();
    }

    @Test
    public void Earn_points_through_the_queue() throws Exception {
        // given
        PointDto requestDto = PointDto.builder()
                .userId(10L)
                .savePoint(1000L)
                .description("buy laptop")
                .build();

        sample2Listener.setCountDownLatch(new CountDownLatch(1));

        // when
        messagingTemplate.convertAndSend("sample2", requestDto);

        // then
        assertTrue(this.sample2Listener.getCountDownLatch().await(15, TimeUnit.SECONDS));
        List<Point> points = pointRepository.findAll();
        assertThat(points.isEmpty()).isEqualTo(false);
        assertThat(points.get(0).getPoint()).isEqualTo(1000L);
    }

    @Test
    public void Earn_points_through_the_queue2() throws Exception {
        // given
        PointDto requestDto = PointDto.builder()
                .userId(1L)
                .savePoint(2000L)
                .description("buy laptop")
                .build();

        sample2Listener.setCountDownLatch(new CountDownLatch(1));

        // when
        messagingTemplate.convertAndSend("sample2", requestDto);

        // then
        assertTrue(this.sample2Listener.getCountDownLatch().await(15, TimeUnit.SECONDS));
        List<Point> points = pointRepository.findAll();
        assertThat(points.isEmpty()).isEqualTo(false);
        assertThat(points.get(0).getId()).isEqualTo(1L);
        assertThat(points.get(0).getPoint()).isEqualTo(2000L);
    }
}
