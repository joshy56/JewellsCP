package me.joshy23.jcp;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

/**
 * Creado por: joshy23
 * El 29/8/2020
 **/
public class JewellsCP extends JavaPlugin {

    public void onEnable() {
        getLogger().info("Enabled.");
    }

    public void onDisable() {
        closeConnections();
        getLogger().info("Disabled.");
    }


    private void closeConnections(){
        Map<JavaPlugin, PluginConnection> connections = PluginConnectionManager.getInstance().getConnections();
        for (PluginConnection connection :
                connections.values()) {
            connection.closePool();
        }
        connections.clear();
    }

}
