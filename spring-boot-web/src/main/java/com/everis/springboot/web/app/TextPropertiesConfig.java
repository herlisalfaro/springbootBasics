package com.everis.springboot.web.app;

import org.springframework.context.annotation.*;


@Configuration
@PropertySources({
    @PropertySource("classpath:texts.properties")
})
public class TextPropertiesConfig {

}
