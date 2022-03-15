package org.zutjmx.apiservlet.webapp.headers.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> listar() throws Exception;
    T porid(Long id) throws Exception;
    void guardar(T t) throws Exception;
    void eliminar(Long id) throws Exception;
}
