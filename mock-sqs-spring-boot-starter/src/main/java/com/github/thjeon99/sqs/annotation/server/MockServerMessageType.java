package com.github.thjeon99.sqs.annotation.server;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by thjeon99@gmail.com on 2020. 9. 4.
 * 
 * Github : https://github.com/thjeon99
 */

@Getter
@RequiredArgsConstructor
public enum MockServerMessageType {
    USE_SERVER("Use Created Mock Sqs Server"),
    CREATE_SERVER("Create Mock Sqs Server");

    private final String message;
}
