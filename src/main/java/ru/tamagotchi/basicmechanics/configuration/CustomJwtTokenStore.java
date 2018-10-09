package ru.tamagotchi.basicmechanics.configuration;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Map;

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
        Map<String, Object> information = token.getAdditionalInformation();
        checkUserIdClaim(information);
        oAuth2Authentication.setDetails(information);
        return oAuth2Authentication;
    }

    private void checkUserIdClaim(Map<String, Object> information) {
        Object userId = information.get("user_id");
        if (!(userId instanceof Integer)) {
            throw new InvalidTokenException("invalid token");
        }
    }
}
