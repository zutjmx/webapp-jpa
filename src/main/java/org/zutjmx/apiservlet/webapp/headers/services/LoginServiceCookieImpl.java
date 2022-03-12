package org.zutjmx.apiservlet.webapp.headers.services;

import jakarta.enterprise.inject.Alternative;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

//@Alternative
public class LoginServiceCookieImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies() != null ? request.getCookies(): new Cookie[0];
        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(
                        cookie -> "username".equals(cookie.getName())
                )
                .map(Cookie::getValue)
                .findAny();
        return cookieOptional;
    }
}
