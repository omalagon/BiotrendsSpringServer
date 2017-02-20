package com.biotrends.config;
/**
 * @author Oscar Malagon
 * @since 8/12/2016.
 */
public class Constants {

    public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/training?autoReconnect=true&useSSL=false";
    public static final String DATABASE_USER = "root";
    public static final String DATABASE_PASSWORD = "arcgis2015";
    public static final String DATABASE_SCHEMA = "training";
    public static final String NAMING_STRATEGY = "com.biotrends.config.repository.NamingStrategy";
    public static final String HIBERNATE_SHOW_SQL_VALUE = "true";
    public static final String HIBERNATE_DIALECT_VALUE = "org.hibernate.dialect.MySQLDialect";
    public static final String HIBERNATE_FORMAT_SQL_VALUE = "false";


    public static final String OLD_DATABASE_URL = "jdbc:mysql://localhost:3306/biotrends_new?autoReconnect=true&useSSL=false";
    public static final String OLD_DATABASE_USER = "root";
    public static final String OLD_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    public static final String OLD_DATABASE_PASSWORD = "arcgis2015";
}
