package org.zutjmx.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import org.zutjmx.apiservlet.webapp.headers.configs.MariaDBConn;
import org.zutjmx.apiservlet.webapp.headers.configs.Repository;
import org.zutjmx.apiservlet.webapp.headers.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository{

    @Inject
    //@Named("connection")
    @MariaDBConn
    private Connection connection;

    private static String sqlPorUsuario = "SELECT * FROM usuarios WHERE username = ?";
    private static String sqlPorTodos = "SELECT * FROM usuarios order by id";

    /*public UsuarioRepositoryImpl(Connection connection) {
        this.connection = connection;
    }*/

    @Override
    public List<Usuario> listar() throws SQLException {
        return null;
    }

    @Override
    public Usuario porid(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlPorUsuario)) {
            preparedStatement.setString(1,username);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = getUsuario(resultSet);
                }
            }
        }
        return usuario;
    }

    private Usuario getUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario;
        usuario = new Usuario();
        usuario.setId(resultSet.getLong("id"));
        usuario.setUsername(resultSet.getString("username"));
        usuario.setPassword(resultSet.getString("password"));
        usuario.setEmail(resultSet.getString("email"));
        return usuario;
    }
}
