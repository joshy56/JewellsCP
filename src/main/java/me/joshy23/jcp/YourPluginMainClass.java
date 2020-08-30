package me.joshy23.jcp;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Creado por: joshy23
 * El 30/8/2020
 **/
public class YourPluginMainClass extends JavaPlugin {

    public void onEnable() {
        PluginConnection connection = new PluginConnection(this, "localhost", 3306, "anotherDB", "joshy", "12345");
    }
}
