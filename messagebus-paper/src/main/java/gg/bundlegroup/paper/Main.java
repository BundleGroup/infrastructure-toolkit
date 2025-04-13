package gg.bundlegroup.paper;

import gg.bundlegroup.messagebus.common.MessageBusPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private MessageBusPlugin plugin;

    @Override
    public void onEnable() {
        plugin = new MessageBusPlugin(getSLF4JLogger(), getDataFolder());
        if (!plugin.enable()) {
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        plugin.disable();
        plugin = null;
    }
}
