package ru.tamagotchi.basicmechanics.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by makar
 * 07.10.2018 10:53
 */
@Getter
@Configuration
@PropertySource("classpath:version.properties")
public class VersionProperties {

    private static final String VERSION_FORMAT = "%s.%s.%s.%s";

    @Value("${git.build.version}")
    private String version;

    @Value("${git.branch}")
    private String branch;

    @Value("${git.commit.id.abbrev}")
    private String hash;

    @Value("${git.total.commit.count}")
    private String count;

    public String format() {
        return String.format(VERSION_FORMAT, version, branch, hash, count);
    }
}
