package org.zutjmx.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.zutjmx.apiservlet.webapp.headers.configs.Repository;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Categoria;

import java.util.List;

@Repository
public class CategoriaRepositoryJpaImpl implements CrudRepository<Categoria> {

    @Inject
    private EntityManager entityManager;

    @Override
    public List<Categoria> listar() throws Exception {
        return entityManager
                .createQuery("from Categoria",Categoria.class)
                .getResultList();
    }

    @Override
    public Categoria porid(Long id) throws Exception {
        return entityManager.find(Categoria.class,id);
    }

    @Override
    public void guardar(Categoria categoria) throws Exception {
        if (categoria.getId() != null && categoria.getId() > 0) {
            entityManager.merge(categoria);
        } else {
            entityManager.persist(categoria);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        Categoria categoria = porid(id);
        entityManager.remove(categoria);
    }
}
