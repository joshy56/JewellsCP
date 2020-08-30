package me.joshy23.jcp;

import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Creado por: joshy23
 * El 29/8/2020
 **/
public class PluginConnection {
    private final HikariDataSource hikariDS;

    public PluginConnection(JavaPlugin parent, String host, int port, String database, String username, String password){
        hikariDS = new HikariDataSource();
        hikariDS.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikariDS.addDataSourceProperty("sourceName", host);
        hikariDS.addDataSourceProperty("port", port);
        hikariDS.addDataSourceProperty("databaseName", database);
        hikariDS.addDataSourceProperty("username", username);
        hikariDS.addDataSourceProperty("pass", password);
        hikariDS.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariDS.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        hikariDS.addDataSourceProperty("cachePrepStmts", true);
        hikariDS.addDataSourceProperty("useServerPrepStmts", true);
        PluginConnectionManager.getInstance().getConnections().put(parent, this);
    }

    public Connection getConnection() throws SQLException {
        return hikariDS.getConnection();
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return hikariDS.getConnection(username, password);
    }

    public void closePool(){
        if(!hikariDS.isClosed()) hikariDS.close();
    }

}
