package com.github.thjeon99.sqs.annotation.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import com.github.thjeon99.sqs.exception.SqsMockException;

import static com.github.thjeon99.sqs.annotation.server.MockServerConstant.SQS_SERVER_PORT;
import static com.github.thjeon99.sqs.annotation.server.MockServerConstant.SQS_SERVER_RANDOM_PORT_ENABLED;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by thjeon99@gmail.com on 2020. 9. 4.
 * 
 * Github : https://github.com/thjeon99
 */

@Slf4j
public abstract class OnMockSqsServerBaseCondition extends SpringBootCondition {

    boolean isRunning(ConditionContext context){
        Environment environment = context.getEnvironment();
        if(isRandomPort(environment)){
            return false;
        }

        return isRunning(getSqsServerPort(environment));
    }

    boolean isRandomPort(Environment environment){
        return "true".equals(environment.getProperty(SQS_SERVER_RANDOM_PORT_ENABLED));
    }

    String getSqsServerPort(Environment environment) {
        return environment.getProperty(SQS_SERVER_PORT);
    }

    boolean isRunning(String port) {
        verifyOS();  // check OS

        String line;
        StringBuilder pidInfo = new StringBuilder();
        Process p = executeGrepProcessCommand(port);

        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {

            while ((line = input.readLine()) != null) {
                pidInfo.append(line);
            }

        } catch (Exception e) {
            String message = "isRunning Check Exception";
            log.error(message , e);
            throw new SqsMockException(message, e);
        }

        return !StringUtils.isEmpty(pidInfo.toString());
    }

    private Process executeGrepProcessCommand(String port) {
        String command = String.format("netstat -nat | grep LISTEN|grep %s", port);
        String[] shell = {"/bin/sh", "-c", command};
        try {
            return Runtime.getRuntime().exec(shell);
        } catch (IOException e) {
            String message = String.format("Execute Command Fail. command: %s",command);
            log.error(message , e);
            throw new SqsMockException(message, e);
        }
    }

    private void verifyOS() {
        if(isWindowsOS()){
            String message = "Not available on Windows OS";
            log.error(message);
            throw new SqsMockException(message);
        }
    }

    boolean isWindowsOS(){
        String os = System.getProperty("os.name");
        return os.contains("Windows");
    }

    String createMessage(boolean isRunning) {
        MockServerMessageType messageType = isRunning ? MockServerMessageType.USE_SERVER : MockServerMessageType.CREATE_SERVER;
        return messageType.getMessage();
    }
}
