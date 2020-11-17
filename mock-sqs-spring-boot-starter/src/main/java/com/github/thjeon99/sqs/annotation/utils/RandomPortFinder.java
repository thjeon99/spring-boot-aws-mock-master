package com.github.thjeon99.sqs.annotation.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import com.github.thjeon99.sqs.exception.SqsMockException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by thjeon99@gmail.com on 2020. 9. 21.
 * 
 * Github : https://github.com/thjeon99
 */

@Slf4j
public class RandomPortFinder {

    public static int findAvailablePort() {
        for (int i=0; i<1000; i++) {
            try {
                int port = getRandomPort();
                if(!isRunning(executeGrepProcessCommand(port))){
                    return port;
                }
            } catch (IOException ex) {
            }
        }

        String message = "Not Found Available port: 10000 ~ 65535";
        log.error(message);
        throw new SqsMockException(message);
    }

    public static int getRandomPort() {
        return (int) (Math.random() * 50000) + 10000;
    }

    private static boolean isRunning(Process p){
        String line;
        StringBuilder pidInfo = new StringBuilder();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {

            while ((line = input.readLine()) != null) {
                pidInfo.append(line);
            }

        } catch (Exception e) {
        }

        return !StringUtils.isEmpty(pidInfo.toString());
    }

    private static Process executeGrepProcessCommand(int port) throws IOException {
        String command = String.format("netstat -nat | grep LISTEN|grep %d", port);
        String[] shell = {"/bin/sh", "-c", command};
        return Runtime.getRuntime().exec(shell);
    }
}
