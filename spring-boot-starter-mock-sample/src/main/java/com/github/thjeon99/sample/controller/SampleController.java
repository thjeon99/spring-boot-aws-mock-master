package com.github.thjeon99.sample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Created by thjeon99@gmail.com on 2020. 10. 23.
 * 
 * Github : https://github.com/thjeon99
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class SampleController {

    private final QueueMessagingTemplate messagingTemplate;

    @GetMapping("/sample")
    public String save(){
        messagingTemplate.convertAndSend("sample", "sample: "+ LocalDate.now().toString());
        return "success";
    }
}
