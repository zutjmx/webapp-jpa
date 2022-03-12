package org.zutjmx.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.zutjmx.apiservlet.webapp.headers.services.LoginService;
import org.zutjmx.apiservlet.webapp.headers.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> username = loginService.getUsername(req);

        if (username.isPresent()) {
            HttpSession session = req.getSession();
            session.invalidate();
        }

        resp.sendRedirect(req.getContextPath() + "/login.html");

    }
}
