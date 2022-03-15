package org.zutjmx.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.zutjmx.apiservlet.webapp.headers.util.JpaUtil;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {

    @Inject
    private Logger logger;

    @Resource(name = "jdbc/myMariaDB")
    private DataSource ds;

    @Produces
    @RequestScoped
    //@Named("connection")
    @MariaDBConn
    private Connection beanConnection() throws NamingException, SQLException {

        /*Context initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/myMariaDB");*/

        return ds.getConnection();
    }

    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint
                .getMember()
                .getDeclaringClass()
                .getName());
    }

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager() {
        return JpaUtil.getEntityManager();
    }

    public void close(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
            logger.info(":: Cerrando la conexión del EntityManager ::");
        }
    }

    public void close(@Disposes @MariaDBConn Connection connection) throws SQLException {
        connection.close();
        logger.info(":: Cerrando la conexión a @MariaDBConn ::");
    }
}
