package org.zutjmx.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.zutjmx.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import org.zutjmx.apiservlet.webapp.headers.models.Carro;
import org.zutjmx.apiservlet.webapp.headers.models.ItemCarro;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Producto;
import org.zutjmx.apiservlet.webapp.headers.services.ProductoService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService productoService;

    @Inject
    private Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        /*Connection connection = (Connection) req.getAttribute("connection");
        ProductoService productoService = new ProductoServiceJdbcImpl(connection);*/

        Optional<Producto> producto = productoService.porId(id);
        if (producto.isPresent()) {
            ItemCarro itemCarro = new ItemCarro(1,producto.get());
            //Se comenta para implementar CDI
            /*HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");*/
            carro.addItemCarro(itemCarro);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
