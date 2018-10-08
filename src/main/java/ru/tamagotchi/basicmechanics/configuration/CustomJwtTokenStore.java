package ru.tamagotchi.basicmechanics.configuration;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Created by makar
 * 07.10.2018 23:33
 */
public class CustomJwtTokenStore extends JwtTokenStore {
    CustomJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        OAuth2Authentication oAuth2Authentication = super.readAuthentication(token);
        oAuth2Authentication.setDetails(token.getAdditionalInformation());
        return oAuth2Authentication;
    }
}
