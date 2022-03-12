package org.zutjmx.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.zutjmx.apiservlet.webapp.headers.configs.MariaDBConn;
import org.zutjmx.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.sql.Connection;
import java.util.logging.Logger;

@TransactionalJdbc
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MariaDBConn
    private Connection connection;

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {
        if (connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }

        try {

            logger.info("++++++ Iniciando transacción en el método "
                    + invocationContext.getMethod().getName()
                    + " de la clase " +
                    invocationContext.getMethod().getDeclaringClass()
                    + " ++++++"
            );

            Object resultado = resultado = invocationContext.proceed();
            connection.commit();

            logger.info("++++++ Realizando commit y Finalizando transacción en el método "
                    + invocationContext.getMethod().getName()
                    + " ++++++"
            );

            return resultado;
        } catch (ServiceJdbcException e) {
            connection.rollback();
            throw e;
        }

    }

}
