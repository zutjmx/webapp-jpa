package org.zutjmx.apiservlet.webapp.headers.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.zutjmx.apiservlet.webapp.headers.configs.MariaDBConn;
import org.zutjmx.apiservlet.webapp.headers.configs.Repository;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Categoria;
import org.zutjmx.apiservlet.webapp.headers.models.entities.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ProductoRepositoryJdbcImpl implements CrudRepository<Producto> {

    @Inject
    private Logger logger;

    @Inject
    //@Named("connection")
    @MariaDBConn
    private Connection connection;

    //Cadenas de consultas Sql
    private static String consultaSqlTodos = "SELECT p.*, c.nombre AS nombre_categoria FROM productos AS p INNER JOIN categorias AS c ON (p.categoria_id = c.id)";
    private static String consultaSqlById = consultaSqlTodos.concat(" WHERE p.id = ?");
    private static String ordenByProductoId = "ORDER BY p.id";
    private static String insertaSql = "insert into productos (nombre, precio, sku, categoria_id, fecha_registro) values(?,?,?,?,?)";
    private static String actualizaSql = "update productos set nombre = ?, precio = ?, sku = ?, categoria_id = ? where id = ?";
    private static String borrarSlq = "delete from productos where id = ?";

    /*public ProductoRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }*/

    @PostConstruct
    public void inicializar() {
        logger.info(":: Inicializando el bean " + getClass().getName() +" ::");
    }

    @PreDestroy
    public void destruir() {
        logger.info(":: Destruyendo el bean " + getClass().getName() +" ::");
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consultaSqlTodos
                    .concat(" ")
                    .concat(ordenByProductoId))) {
            while (resultSet.next()) {
                Producto producto = getProducto(resultSet);
                productos.add(producto);
            }
        }

        return productos;
    }

    @Override
    public Producto porid(Long id) throws SQLException {
        Producto producto = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSqlById)) {
            preparedStatement.setLong(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    producto = getProducto(resultSet);
                }
                preparedStatement.close();
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            //Se ejecuta update
            sql = actualizaSql;
        } else {
            //Se ejecuta insert
            sql = insertaSql;
        }
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setInt(2, producto.getPrecio());
            preparedStatement.setString(3, producto.getSku());
            preparedStatement.setLong(4, producto.getCategoria().getId());
            if (producto.getId() != null && producto.getId() > 0) {
                preparedStatement.setLong(5,producto.getId());
            } else {
                preparedStatement.setDate(5, Date.valueOf(producto.getFechaRegistro()));
            }
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = borrarSlq;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }
    }

    private Producto getProducto(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultSet.getLong("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getInt("precio"));
        producto.setSku(resultSet.getString("sku"));
        producto.setFechaRegistro(resultSet.getDate("fecha_registro").toLocalDate());

        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getLong("categoria_id"));
        categoria.setNombre(resultSet.getString("nombre_categoria"));
        producto.setCategoria(categoria);

        return producto;
    }

}
