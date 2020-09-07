package me.joshy23.jcp;

import com.zaxxer.hikari.HikariDataSource;
import me.joshy23.jcp.events.PluginQueryEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author joshy23
 * @since 29/8/2020
 **/
public class PluginConnection {
    private HikariDataSource hikariDS;
    private Properties properties;
    private final Engine engine;

    public PluginConnection(JavaPlugin parent, Engine engine, String host, int port, String database, String username, String password) {
        this.engine = engine;
        properties = new Properties();
        properties.setProperty("dataSourceClassName", engine.getDataSource());
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

    public void reopenPool() {
        if (hikariDS.isClosed()) hikariDS = new HikariDataSource();
        hikariDS.setDataSourceProperties(properties);
    }

    protected void closePool() {
        if (!hikariDS.isClosed()) hikariDS.close();
    }

    public ResultSet query(String sql, Object[] elements) {
        try (Connection connection = hikariDS.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet;
            int length = elements.length;
            for (int i = 0; i < length; i++) {
                statement.setObject(i, elements[i]);
            }
            resultSet = statement.executeQuery();
            Bukkit.getPluginManager().callEvent(new PluginQueryEvent(this));
            return resultSet;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

}
