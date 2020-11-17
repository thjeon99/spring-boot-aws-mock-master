package com.github.thjeon99.sqs.annotation.utils;

import org.junit.Test;

import com.github.thjeon99.sqs.annotation.utils.RandomPortFinder;

import java.net.ServerSocket;

import static org.junit.Assert.assertTrue;

/**
 * Created by thjeon99@gmail.com on 2020. 9. 21.
 * 
 * Github : https://github.com/thjeon99
 */

public class RandomPortFinderTest {

    @Test
    public void get_available_port() throws Exception {
        //given
        int usePort = 10000;
        ServerSocket serverSocket = new ServerSocket(usePort);

        //when
        int availablePort = RandomPortFinder.findAvailablePort();

        //then
        assertTrue(availablePort != usePort);
        serverSocket.close();
    }
}
