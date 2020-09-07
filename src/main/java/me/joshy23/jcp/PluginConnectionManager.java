package me.joshy23.jcp;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * @author joshy23
 * @since 29/8/2020
 **/
public class PluginConnectionManager {
    private HashMap<JavaPlugin, PluginConnection> connections;
    private static PluginConnectionManager pluginConnectionManager;

    private PluginConnectionManager(){
        connections = new HashMap<>();
    }

    public static PluginConnectionManager getInstance(){
        if(pluginConnectionManager == null) return (pluginConnectionManager = new PluginConnectionManager());
        return pluginConnectionManager;
    }

    public HashMap<JavaPlugin, PluginConnection> getConnections() {
        return connections;
    }

}
