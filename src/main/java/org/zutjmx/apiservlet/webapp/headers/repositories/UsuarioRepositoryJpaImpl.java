package org.zutjmx.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.zutjmx.apiservlet.webapp.headers.configs.Repository;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Usuario;

import java.util.List;

@RepositoryJpa
@Repository
public class UsuarioRepositoryJpaImpl implements UsuarioRepository{

    @Inject
    private EntityManager entityManager;

    @Override
    public List<Usuario> listar() throws Exception {
        return entityManager
                .createQuery("from Usuario",Usuario.class)
                .getResultList();
    }

    @Override
    public Usuario porid(Long id) throws Exception {
        return entityManager.find(Usuario.class,id);
    }

    @Override
    public void guardar(Usuario usuario) throws Exception {
        if (usuario.getId() != null && usuario.getId() > 0) {
            entityManager.merge(usuario);
        } else {
            entityManager.persist(usuario);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        Usuario usuario = porid(id);
        entityManager.remove(usuario);
    }

    @Override
    public Usuario porUsername(String username) throws Exception {
        String consultaPorUsername = "select u from Usuario u " +
                "where u.username = :username";
        return entityManager
                .createQuery(consultaPorUsername,Usuario.class)
                .setParameter("username",username)
                .getSingleResult();
    }
}
