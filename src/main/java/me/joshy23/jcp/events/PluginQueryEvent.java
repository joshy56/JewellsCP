package me.joshy23.jcp.events;

import me.joshy23.jcp.PluginConnection;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author joshy23
 * @since 5/9/2020
 **/
public class PluginQueryEvent extends Event {
    private final PluginConnection connection;
    private static final HandlerList handlers = new HandlerList();

    public PluginQueryEvent(PluginConnection connection){
        this.connection = connection;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public PluginConnection getConnection() {
        return connection;
    }

}
