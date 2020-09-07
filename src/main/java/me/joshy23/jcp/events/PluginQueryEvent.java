package me.joshy23.jcp.events;

import me.joshy23.jcp.PluginConnection;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.sql.ResultSet;

/**
 * @author joshy23
 * @since 5/9/2020
 **/
public class PluginQueryEvent extends Event {
    private final PluginConnection pluginConnection;
    private final ResultSet resultSet;
    private static final HandlerList handlers = new HandlerList();

    public PluginQueryEvent(PluginConnection connection, ResultSet resultSet){
        pluginConnection = connection;
        this.resultSet = resultSet;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    public PluginConnection getPluginConnection() {
        return pluginConnection;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }
}
