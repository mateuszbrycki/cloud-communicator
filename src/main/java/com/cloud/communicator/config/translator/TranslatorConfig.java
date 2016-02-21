package com.cloud.communicator.config.translator;

import com.cloud.communicator.translator.TranslatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Mateusz on 21.02.2016.
 */
@Configuration
public class TranslatorConfig {

    @Bean
    public TranslatorFactory translatorFactory() {
        return new TranslatorFactory();
    }
}
