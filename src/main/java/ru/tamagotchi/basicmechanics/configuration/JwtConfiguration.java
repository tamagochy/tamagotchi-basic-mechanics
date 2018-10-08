package ru.tamagotchi.basicmechanics.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by makar
 * 08.10.2018 20:41
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtConfiguration {
    private String key;
}
