package ru.tamagotchi.basicmechanics.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import ru.tamagotchi.basicmechanics.exception.handler.impl.SecurityExceptionHandler;

/**
 * Created by makar
 * 07.10.2018 22:20
 */
@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String[] ALLOWED_PATHS = {
            "/version",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**"
    };

    private final JwtConfiguration configuration;
    private final SecurityExceptionHandler exceptionHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(exceptionHandler);
        // @formatter:off
        http.formLogin().disable()
            .cors().and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers(ALLOWED_PATHS)
                .permitAll()
            .and()
            .authorizeRequests()
                .anyRequest()
                .authenticated();
        // @formatter:on
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }

    @Bean
    public TokenStore tokenStore() {
        return new CustomJwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(configuration.getKey());
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }
}
