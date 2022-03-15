package org.zutjmx.apiservlet.webapp.headers.repositories;

import org.zutjmx.apiservlet.webapp.headers.models.entities.Usuario;

import java.util.List;

public class UsuarioRepositoryJpaImpl implements UsuarioRepository{
    @Override
    public List<Usuario> listar() throws Exception {
        return null;
    }

    @Override
    public Usuario porid(Long id) throws Exception {
        return null;
    }

    @Override
    public void guardar(Usuario usuario) throws Exception {

    }

    @Override
    public void eliminar(Long id) throws Exception {

    }

    @Override
    public Usuario porUsername(String username) throws Exception {
        return null;
    }
}
