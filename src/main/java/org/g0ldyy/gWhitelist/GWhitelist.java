package org.g0ldyy.gWhitelist;

import org.bukkit.plugin.java.JavaPlugin;

public final class GWhitelist extends JavaPlugin {
    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        WhitelistManager whitelistManager = new WhitelistManager(this);

        WhitelistCommand whitelistCommand = new WhitelistCommand(whitelistManager);
        this.getCommand("gwhitelist").setExecutor(whitelistCommand);
        this.getCommand("gwhitelist").setTabCompleter(whitelistCommand);

        getServer().getPluginManager().registerEvents(new WhitelistListener(whitelistManager), this);

        getLogger().info("gWhitelist enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("gWhitelist disabled!");
    }
}
