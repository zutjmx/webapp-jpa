package org.zutjmx.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import org.zutjmx.apiservlet.webapp.headers.configs.MariaDBConn;
import org.zutjmx.apiservlet.webapp.headers.configs.Repository;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaRepositoryImpl implements CrudRepository<Categoria> {

    private Connection connection;

    private static String consultaSqlTodos = "SELECT id, nombre FROM categorias";
    private static String consultaSqlById = consultaSqlTodos.concat(" WHERE id = ?");
    private static String ordenById = "ORDER BY id";
    private static String borrarSlq = "delete from categorias where id = ?";

    @Inject
    public CategoriaRepositoryImpl(/*@Named("connection")*/@MariaDBConn Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consultaSqlTodos.concat(" ").concat(ordenById))) {
            while (resultSet.next()) {
                Categoria categoria = getCategoria(resultSet);
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porid(Long id) throws SQLException {
        Categoria categoria = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSqlById)) {
            preparedStatement.setLong(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    categoria = getCategoria(resultSet);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private Categoria getCategoria(ResultSet resultSet) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getLong("id"));
        categoria.setNombre(resultSet.getString("nombre"));
        return categoria;
    }

}
