package org.zutjmx.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object logging(InvocationContext invocationContext) throws Exception {
        logger.info("===== Entrando antes de invocar el método "
                + invocationContext.getMethod().getName()
                + " de la clase " +
                invocationContext.getMethod().getDeclaringClass()
                + " ====="
        );
        Object resultado = invocationContext.proceed();
        logger.info("===== Saliendo de invocar el método "
                + invocationContext.getMethod().getName()
                + " ====="
        );
        return resultado;
    }
}
