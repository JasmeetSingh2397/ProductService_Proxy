package com.example.personal_productserviceproxy.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Long, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Long, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}

