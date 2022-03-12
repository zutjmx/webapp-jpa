package org.zutjmx.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.zutjmx.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import org.zutjmx.apiservlet.webapp.headers.models.Producto;
import org.zutjmx.apiservlet.webapp.headers.services.LoginService;
import org.zutjmx.apiservlet.webapp.headers.services.LoginServiceSessionImpl;
import org.zutjmx.apiservlet.webapp.headers.services.ProductoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos", "/productos.html"})
public class ProductoServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService productoService;

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Connection connection = (Connection) req.getAttribute("connection");
        ProductoService productoService = new ProductoServiceJdbcImpl(connection);*/

        List<Producto> productos = productoService.listar();
        String servletPath = req.getServletPath();

        /*LoginService loginService = new LoginServiceSessionImpl();*/
        Optional<String> sessionOptional = loginService.getUsername(req);

        req.setAttribute("productos",productos);
        req.setAttribute("username",sessionOptional);
        req.setAttribute("titulo",req.getAttribute("titulo") + ": Listado Productos");

        getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);

    }
}
