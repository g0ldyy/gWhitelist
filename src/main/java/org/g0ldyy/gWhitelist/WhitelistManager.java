package org.g0ldyy.gWhitelist;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WhitelistManager {
    private final JavaPlugin plugin;
    private final Gson gson = new Gson();
    private final Set<String> whitelist;
    private final File whitelistFile;
    private boolean enabled;

    public WhitelistManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.whitelistFile = new File(plugin.getDataFolder(), "whitelist.json");
        this.whitelist = loadWhitelist();
        this.enabled = loadWhitelistState();
    }

    public void reload() {
        this.whitelist.clear();
        this.whitelist.addAll(loadWhitelist());
        this.enabled = loadWhitelistState();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        saveWhitelistState();
    }

    public boolean isWhitelisted(String playerName) {
        return whitelist.contains(playerName);
    }

    public void addPlayer(String playerName) {
        whitelist.add(playerName);
        saveWhitelist();
    }

    public void removePlayer(String playerName) {
        whitelist.remove(playerName);
        saveWhitelist();
    }

    public Set<String> getWhitelist() {
        return new HashSet<>(whitelist);
    }

    public Set<String> loadWhitelist() {
        if (!whitelistFile.exists()) {
            return new HashSet<>();
        }

        try (FileReader reader = new FileReader(whitelistFile)) {
            return gson.fromJson(reader, new TypeToken<Set<String>>() {}.getType());
        } catch (IOException e) {
            plugin.getLogger().severe("Could not load whitelist: " + e.getMessage());
            return new HashSet<>();
        }
    }

    public void saveWhitelist() {
        try (FileWriter writer = new FileWriter(whitelistFile)) {
            gson.toJson(whitelist, writer);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save whitelist: " + e.getMessage());
        }
    }

    public boolean loadWhitelistState() {
        plugin.reloadConfig();
        return plugin.getConfig().getBoolean("whitelist-enabled", false);
    }

    public void saveWhitelistState() {
        plugin.getConfig().set("whitelist-enabled", enabled);
        plugin.saveConfig();
    }
}
