package ru.tamagotchi.basicmechanics.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.service.api.AuthService;

import java.util.Map;

/**
 * Created by makar
 * 08.10.2018 20:37
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final String USER_ID_KEY = "user_id";

    @Override
    public Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        return (Integer) ((Map) details.getDecodedDetails()).get(USER_ID_KEY);
    }
}
