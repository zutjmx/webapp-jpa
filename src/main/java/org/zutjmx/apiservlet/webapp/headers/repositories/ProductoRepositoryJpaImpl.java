package org.zutjmx.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.zutjmx.apiservlet.webapp.headers.configs.Repository;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Producto;

import java.util.List;

@RepositoryJpa
@Repository
public class ProductoRepositoryJpaImpl implements CrudRepository<Producto> {

    @Inject
    private EntityManager entityManager;

    @Override
    public List<Producto> listar() throws Exception {
        String consultaProductos = "select p from Producto p " +
                "left outer join fetch p.categoria";
        return entityManager
                .createQuery(consultaProductos,Producto.class)
                .getResultList();
    }

    @Override
    public Producto porid(Long id) throws Exception {
        return entityManager
                .find(Producto.class,id);
    }

    @Override
    public void guardar(Producto producto) throws Exception {
        if (producto.getId() != null && producto.getId() > 0) {
            entityManager.merge(producto);
        } else {
            entityManager.persist(producto);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        Producto producto = porid(id);
        entityManager.remove(producto);
    }
}
