package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.config.app;

//import com.fasterxml.jackson.databind.Module;
//import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableJpaAuditing
@EnableRedisRepositories
public class AppConfig {
//    @Bean
//    public Module hibernateModule() {
//        return new Hibernate5JakartaModule();
//    }
}
