package org.zutjmx.apiservlet.webapp.headers.services;

import org.zutjmx.apiservlet.webapp.headers.models.entities.Categoria;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);

    List<Categoria> listarCategorias();

    Optional<Categoria> porIdCategoria(Long id);
}
