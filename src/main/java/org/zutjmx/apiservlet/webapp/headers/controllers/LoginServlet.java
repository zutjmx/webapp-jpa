package org.zutjmx.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Usuario;
import org.zutjmx.apiservlet.webapp.headers.services.LoginService;
import org.zutjmx.apiservlet.webapp.headers.services.UsuarioService;

import java.io.IOException;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> sessionOptional = loginService.getUsername(req);

        if (sessionOptional.isPresent()) {

            /*resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("      <meta charset=\"UTF-8\">");
                out.println("      <title>Hola "+sessionOptional.get()+"</title>");
                out.println("    </head>");
                out.println("  <body>");
                out.println("      <h1>Hola "+sessionOptional.get()+" has iniciado sesión con éxito</h1>");
                out.println("      <p><a href=\"" + req.getContextPath() + "\">Regresar</a></p>");
                out.println("      <p><a href='" + req.getContextPath() + "/logout'>Cerrar sesión</a></p>");
                out.println("  </body>");
                out.println("</html>");
            }*/

            resp.sendRedirect(req.getContextPath() + "/welcome.jsp");
            
        } else {
            req.setAttribute("titulo",req.getAttribute("titulo") + ": Acceso");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        /*Connection connection = (Connection) req.getAttribute("connection");
        UsuarioService usuarioService = new UsuarioServiceImpl(connection);*/

        Optional<Usuario> usuarioOptional = usuarioService.login(username, password);

        if (usuarioOptional.isPresent()) {

            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath() + "/login.html");

        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Usuario y/o contraseña no válidos.");
        }

    }
}
