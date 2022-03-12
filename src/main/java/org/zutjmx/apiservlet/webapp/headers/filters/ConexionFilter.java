package org.zutjmx.apiservlet.webapp.headers.filters;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.zutjmx.apiservlet.webapp.headers.configs.MariaDBConn;
import org.zutjmx.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    /*@Inject
    @MariaDBConn
    private Connection connection;*/

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*try {

            Connection connectionRequest = this.connection;

            if (connectionRequest.getAutoCommit()) {
                connectionRequest.setAutoCommit(false);
            }*/

            try {
                filterChain.doFilter(servletRequest, servletResponse);
                /*connectionRequest.commit();*/
            } catch (/*SQLException |*/ ServiceJdbcException exception) {
                /*connectionRequest.rollback();*/
                ((HttpServletResponse) servletResponse)
                        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,exception.getMessage());
                exception.printStackTrace();
            }

        /*} catch (SQLException*//* | NamingException*//* e) {
            e.printStackTrace();
        }*/
    }
}
