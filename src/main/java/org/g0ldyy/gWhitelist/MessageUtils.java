package org.g0ldyy.gWhitelist;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

public class MessageUtils {
    public static final Component PREFIX = Component.text()
        .content("[")
        .color(NamedTextColor.DARK_GRAY)
        .append(Component.text("gWhitelist", NamedTextColor.AQUA))
        .append(Component.text("] ", NamedTextColor.DARK_GRAY))
        .build();

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(PREFIX.append(Component.text(message, NamedTextColor.GRAY)));
    }

    public static void sendSuccess(CommandSender sender, String message) {
        sender.sendMessage(PREFIX.append(Component.text(message, NamedTextColor.GREEN)));
    }

    public static void sendError(CommandSender sender, String message) {
        sender.sendMessage(PREFIX.append(Component.text(message, NamedTextColor.RED)));
    }
}