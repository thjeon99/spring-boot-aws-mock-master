package com.github.thjeon99.sqs.exception;

import lombok.Getter;

/**
 * Created by thjeon99@gmail.com on 2020. 9. 4.
 * 
 * Github : https://github.com/thjeon99
 */

@Getter
public class SqsMockException extends RuntimeException {

    public SqsMockException(String message) {
        super(message);
    }

    public SqsMockException(String message, Throwable cause) {
        super(message, cause);
    }
}
