package org.zutjmx.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.zutjmx.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Producto;
import org.zutjmx.apiservlet.webapp.headers.services.ProductoService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService productoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*Connection connection = (Connection) req.getAttribute("connection");
        ProductoService productoService = new ProductoServiceJdbcImpl(connection);*/

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if (id > 0) {
            Optional<Producto> optionalProducto = productoService.porId(id);
            if (optionalProducto.isPresent()) {
                productoService.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/productos");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No existe el producto con id: " + id);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"El id del producto se debe de envíar como parámetro en la ruta URL.");
        }
    }
}
