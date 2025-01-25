package org.g0ldyy.gWhitelist;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WhitelistCommand implements CommandExecutor, TabCompleter {
    private final WhitelistManager whitelistManager;

    public WhitelistCommand(WhitelistManager whitelistManager) {
        this.whitelistManager = whitelistManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length < 1) {
            MessageUtils.sendError(sender, "Unknown command. Usage: /gwhitelist <add|remove|list|on|off|status|reload> [player]");
            return true;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "add":
                if (args.length < 2) {
                    MessageUtils.sendError(sender,"Usage: /gwhitelist add <player>");
                    return true;
                }

                whitelistManager.addPlayer(args[1]);
                MessageUtils.sendSuccess(sender,"Player " + args[1] + " added to the whitelist.");
                break;

            case "remove":
                if (args.length < 2) {
                    MessageUtils.sendError(sender,"Usage: /gwhitelist remove <player>");
                    return true;
                }

                whitelistManager.removePlayer(args[1]);
                MessageUtils.sendSuccess(sender,"Player " + args[1] + " removed from the whitelist.");

                if (whitelistManager.isEnabled()) {
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player != null) {
                        player.kick(Component.text("You are not whitelisted on this server."));
                    }
                }

                break;

            case "list":
                Set<String> whitelist = whitelistManager.getWhitelist();
                MessageUtils.sendMessage(sender,"Whitelisted players: " + (whitelist.isEmpty() ? "none" : String.join(", ", whitelist)));
                break;

            case "on":
                whitelistManager.setEnabled(true);
                MessageUtils.sendMessage(sender,"Whitelist enabled.");

                for (Player onlinePlayer: Bukkit.getOnlinePlayers()) {
                    String playerName = onlinePlayer.getName();
                    if (!whitelistManager.isWhitelisted(playerName)) {
                        onlinePlayer.kick(Component.text("You are not whitelisted on this server."));
                    }
                }

                break;

            case "off":
                whitelistManager.setEnabled(false);
                MessageUtils.sendSuccess(sender,"Whitelist disabled.");
                break;

            case "status":
                MessageUtils.sendMessage(sender,"Whitelist is " + (whitelistManager.isEnabled() ? "enabled" : "disabled") + ".");
                break;

            case "reload":
                whitelistManager.reload();
                MessageUtils.sendSuccess(sender, "Whitelist and configuration reloaded.");

                if (whitelistManager.isEnabled()) {
                    for (Player onlinePlayer: Bukkit.getOnlinePlayers()) {
                        String playerName = onlinePlayer.getName();
                        if (!whitelistManager.isWhitelisted(playerName)) {
                            onlinePlayer.kick(Component.text("You are not whitelisted on this server."));
                        }
                    }
                }

                break;

            default:
                MessageUtils.sendError(sender, "Unknown command. Usage: /gwhitelist <add|remove|list|on|off|status|reload> [player]");
                return true;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("add");
            completions.add("remove");
            completions.add("list");
            completions.add("on");
            completions.add("off");
            completions.add("status");
            completions.add("reload");
        } else if (args.length == 2) {
            String subCommand = args[0].toLowerCase();

            if (subCommand.equals("add")) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    String playerName = onlinePlayer.getName();
                    if (!whitelistManager.isWhitelisted(playerName)) {
                        completions.add(playerName);
                    }
                }

                for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                    String playerName = offlinePlayer.getName();
                    if (playerName != null && !whitelistManager.isWhitelisted(playerName)) {
                        completions.add(playerName);
                    }
                }
            } else if (subCommand.equals("remove")) {
                completions.addAll(whitelistManager.getWhitelist());
            }
        }

        if (!args[args.length - 1].isEmpty()) {
            completions.removeIf(suggestion -> !suggestion.toLowerCase().startsWith(args[args.length - 1].toLowerCase()));
        }

        return completions;
    }
}
