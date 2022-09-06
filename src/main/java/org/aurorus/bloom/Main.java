package org.aurorus.bloom;

import org.aurorus.bloom.events.BloomOnClick;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new BloomOnClick(), this);
    }
}
