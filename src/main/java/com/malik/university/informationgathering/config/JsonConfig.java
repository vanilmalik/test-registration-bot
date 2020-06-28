package com.malik.university.informationgathering.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JsonConfig {

    public Jackson2ObjectMapperBuilderCustomizer build() {
        return jacksonObjectMapperBuilder -> {};
    }

}
