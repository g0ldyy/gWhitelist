package org.g0ldyy.gWhitelist;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class WhitelistListener implements Listener {
    private final WhitelistManager whitelistManager;

    public WhitelistListener(WhitelistManager whitelistManager) {
        this.whitelistManager = whitelistManager;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (whitelistManager.isEnabled() && !whitelistManager.isWhitelisted(event.getPlayer().getName())) {
            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "You are not whitelisted on this server.");
        }
    }
}