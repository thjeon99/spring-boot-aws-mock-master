package com.github.thjeon99.sqs.annotation;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/**
 * Created by thjeon99@gmail.com on 2020. 9. 23.
 * 
 * Github : https://github.com/thjeon99
 */

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        environment.setRequiredProperties("sqs.mock.port=20000");
    }
}
