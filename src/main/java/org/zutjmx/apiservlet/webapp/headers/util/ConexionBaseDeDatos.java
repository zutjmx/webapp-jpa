package org.zutjmx.apiservlet.webapp.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBaseDeDatos {

    //Datos para servidor local
    private static String url = "jdbc:mariadb://192.168.1.136:3306/jakartaee9_curso?serverTimezone=America/Mexico_City";
    private static String username = "root";
    private static String password = "sistemas";

    //Datos para MariaDB SkySql
    private static String hostSkySql = "zutjmx-sky-sql-db00005770.mdb0002418.db.skysql.net";
    private static String puertoSkySql = "5051";
    private static String usuarioSkySql = "DB00005770";
    private static String passwordSkySql = "b4LIgzbq=Yd7OD5rJ4ay5VDY";
    private static String rutaSslChainFile ="C:\\desarrollo\\skysql_chain.pem";
    private static String urlSkySql = "jdbc:mariadb://"+hostSkySql+":"+puertoSkySql+"/jakartaee9_curso?serverTimezone=America/Mexico_City";
    private static Properties connConfig;

    //Objeto de conexión
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        //System.out.println("Entró al método ConexionBaseDeDatos.getConnection()");
        configuraConnSkySql();
        //connection = DriverManager.getConnection(url,username,password);
        connection = DriverManager.getConnection(urlSkySql,connConfig);
        return connection;
    }

    private static void configuraConnSkySql() {
        connConfig = new Properties();
        connConfig.setProperty("user",usuarioSkySql);
        connConfig.setProperty("password", passwordSkySql);
        connConfig.setProperty("sslMode", "verify-full");
        connConfig.setProperty("serverSslCert", rutaSslChainFile);
    }

}
