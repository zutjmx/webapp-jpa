package org.zutjmx.apiservlet.webapp.headers.services;

import jakarta.inject.Inject;
import org.zutjmx.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import org.zutjmx.apiservlet.webapp.headers.configs.Service;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Categoria;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Producto;
import org.zutjmx.apiservlet.webapp.headers.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

@Service
@ProductoServicePrincipal
public class ProductoServiceJdbcImpl implements ProductoService{

    @Inject
    private CrudRepository<Producto> productoRepositoryJdbc;

    @Inject
    private CrudRepository<Categoria> categoriaRepository;

    /*public ProductoServiceJdbcImpl(Connection connection) {
        this.productoRepositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
        this.categoriaRepository = new CategoriaRepositoryImpl(connection);
    }*/

    @Override
    public List<Producto> listar() {
        try {
            return productoRepositoryJdbc.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(productoRepositoryJdbc.porid(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            productoRepositoryJdbc.guardar(producto);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            productoRepositoryJdbc.eliminar(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try {
            return categoriaRepository.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(categoriaRepository.porid(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }
}
