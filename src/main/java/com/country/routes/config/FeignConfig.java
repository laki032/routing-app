package com.country.routes.config;

import feign.codec.Decoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableFeignClients(basePackages = {"com.country.routes.gateway"})
public class FeignConfig {

    @Bean
    public Decoder textPlainDecoder() {
        return new SpringDecoder(() -> new HttpMessageConverters(new MappingJackson2HttpMessageConverter() {
            @Override
            public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
                super.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
            }
        }));
    }
}
