package com.example.personal_productserviceproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.personal_productserviceproxy.Repositories")
@EnableElasticsearchRepositories(basePackages = "com.example.personal_productserviceproxy.ElasticSearchRepositories")
public class PersonalProductServiceProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalProductServiceProxyApplication.class, args);
    }

}
