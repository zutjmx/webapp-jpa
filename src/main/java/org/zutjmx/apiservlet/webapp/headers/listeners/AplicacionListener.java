package org.zutjmx.apiservlet.webapp.headers.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.zutjmx.apiservlet.webapp.headers.models.Carro;

@WebListener
public class AplicacionListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log(":: Inicializando la Aplicación ::");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje",":: Algún valor global de la aplicación ::");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //sce.getServletContext().log(":: Destruyendo la Aplicación ::");
        servletContext.log(":: Destruyendo la Aplicación ::");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //sre.getServletContext().log(":: Inicializando Request ::");
        servletContext.log(":: Inicializando Request ::");
        sre.getServletRequest().setAttribute("mensaje",":: Guardando un valor para el request ::");
        sre.getServletRequest().setAttribute("titulo","Catálogo Servlet");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        //sre.getServletContext().log(":: Destruyendo Request ::");
        servletContext.log(":: Destruyendo Request ::");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log(":: Iniciando Sesión Http ::");
        //Se comenta para implementar CDI
        /*Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro",carro);*/
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log(":: Destruyendo Sesión Http ::");
    }
}
