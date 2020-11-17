package com.github.thjeon99.sample.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.thjeon99.sample.dto.PointDto;

/**
 * Created by thjeon99@gmail.com on 2020. 10. 15.
 * 
 * Github : https://github.com/thjeon99
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class Sample3Controller {
    private final QueueMessagingTemplate messagingTemplate;

    @PostMapping("/sample3")
    public String save(@RequestBody PointDto requestDto){
        messagingTemplate.convertAndSend("sample3", requestDto);
        return "success";
    }


}
