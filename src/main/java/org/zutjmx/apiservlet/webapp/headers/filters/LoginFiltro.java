package org.zutjmx.apiservlet.webapp.headers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.zutjmx.apiservlet.webapp.headers.services.LoginService;
import org.zutjmx.apiservlet.webapp.headers.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/carro/*","/productos/form/*","/productos/eliminar/*"})
public class LoginFiltro implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> username = loginService.getUsername((HttpServletRequest) servletRequest);
        if (username.isPresent()) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,":: No autorizado ::");
        }
    }
}
