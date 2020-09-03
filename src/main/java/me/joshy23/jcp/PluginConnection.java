package me.joshy23.jcp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.plugin.java.JavaPlugin;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Creado por: joshy23
 * El 29/8/2020
 **/
public class PluginConnection {
    private HikariDataSource hikariDS;
    private Properties properties;

    public PluginConnection(JavaPlugin parent, String host, int port, String database, String username, String password){
        properties = new Properties();
        properties.setProperty("dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        properties.setProperty("dataSource.serverName", host);
        properties.setProperty("dataSource.portNumber", String.valueOf(port));
        properties.setProperty("dataSource.databaseName", database);
        properties.setProperty("dataSource.user", username);
        properties.setProperty("dataSource.password", password);
        properties.setProperty("prepStmtCacheSize", String.valueOf(250));
        properties.setProperty("prepStmtCacheSqlLimit", String.valueOf(2048));
        properties.setProperty("cachePrepStmts", String.valueOf(true));
        properties.setProperty("useServerPrepStmts", String.valueOf(true));
        properties.put("dataSource.logWriter", new PrintWriter(System.out));
        hikariDS = new HikariDataSource();
        hikariDS.setDataSourceProperties(properties);
        PluginConnectionManager.getInstance().getConnections().put(parent, this);
    }

    public Connection getConnection() throws SQLException {
        return hikariDS.getConnection();
    }

    public void reopenPool(){
        if(hikariDS.isClosed()) hikariDS = new HikariDataSource();
        hikariDS.setDataSourceProperties(properties);
    }

    public void closePool(){
        if(!hikariDS.isClosed()) hikariDS.close();
    }

    public PreparedStatement query(String sql) throws SQLException {
        try(Connection connection = hikariDS.getConnection()){
            return connection.prepareStatement(sql);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

}
