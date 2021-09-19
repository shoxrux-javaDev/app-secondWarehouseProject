package uz.spring.appanotherwarehouseproject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class AutoGenerationMethod {

    @Bean
    public String AutoGeneration() {
        return String.valueOf(UUID.randomUUID());
    }
}
