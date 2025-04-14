package com.srasb.config;

import com.srasb.util.messagecreators.MessageCreator;
import com.srasb.util.messagecreators.RussianMessageCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class Config {

    @Bean
    public MessageCreator getMessageCreator(){
        return new RussianMessageCreator();
    }

}
