package org.zutjmx.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import org.zutjmx.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.util.logging.Logger;

@TransactionalJpa
@Interceptor
public class TransactionalJpaInterceptor {

    @Inject
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {

        try {

            logger.info("++++++ Iniciando transacción en el método "
                    + invocationContext.getMethod().getName()
                    + " de la clase " +
                    invocationContext.getMethod().getDeclaringClass()
                    + " ++++++"
            );

            entityManager.getTransaction().begin();
            Object resultado = resultado = invocationContext.proceed();
            entityManager.getTransaction().commit();

            logger.info("++++++ Realizando commit y Finalizando transacción en el método "
                    + invocationContext.getMethod().getName()
                    + " ++++++"
            );

            return resultado;
        } catch (ServiceJdbcException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }

    }

}
