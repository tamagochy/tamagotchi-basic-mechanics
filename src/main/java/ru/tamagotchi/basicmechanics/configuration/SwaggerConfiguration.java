package ru.tamagotchi.basicmechanics.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * Created by makar
 * 02.10.2018 23:30
 */
@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfiguration {

    private static final String TOKEN = "token";
    private static final String HEADER_NAME = "Authorization";
    private static final String PASS_AS = "header";

    private final VersionProperties versionProperties;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.tamagotchi"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .apiInfo(apiInfo());
    }

    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey(TOKEN, HEADER_NAME, PASS_AS));
    }

    private List<SecurityContext> securityContexts() {
        SecurityContext context = SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference(TOKEN, new AuthorizationScope[0])))
                .forPaths(PathSelectors.regex("/pet.*"))
                .build();
        return Collections.singletonList(context);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Tamagotchi Basic Mechanics API",
                "",
                versionProperties.getVersion(),
                "Terms of service",
                null,
                "License of API",
                "API license URL",
                Collections.emptyList()
        );
    }
}
