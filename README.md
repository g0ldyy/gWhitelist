<p align="center"><img src="https://img.shields.io/modrinth/dt/Li8me3EZ" /></p>

# GWhitelist

GWhitelist is a simple and efficient Minecraft plugin to manage a whitelist. It allows you to easily add, remove, and list whitelisted players, as well as enable or disable the whitelist.

## Features

- **Add/Remove players**: Add or remove players from the whitelist with simple commands.
- **Enable/Disable whitelist**: Turn the whitelist on or off at any time.
- **List whitelisted players**: View all whitelisted players in one command.
- **Reload support**: Reload the whitelist and configuration without restarting the server.

## Commands

- **`/gwhitelist add <player>`**: Add a player to the whitelist.
- **`/gwhitelist remove <player>`**: Remove a player from the whitelist.
- **`/gwhitelist list`**: List all whitelisted players.
- **`/gwhitelist on`**: Enable the whitelist.
- **`/gwhitelist off`**: Disable the whitelist.
- **`/gwhitelist status`**: Check if the whitelist is enabled or disabled.
- **`/gwhitelist reload`**: Reload the whitelist and configuration.

## Permissions

- **`gwhitelist.manage`**: Allows access to all commands.

By default, only server operators have these permissions.

## Installation

1. Download the latest `.jar` file from [Modrinth](https://modrinth.com/plugin/gWhitelist).
2. Place the `.jar` file in your server's `plugins` folder.
3. Restart the server.

## Configuration

The plugin uses two files:

1. **`whitelist.json`**: Stores the list of whitelisted players.
2. **`config.yml`**: Stores the whitelist status (enabled/disabled).

You can manually edit these files, and use `/gwhitelist reload` to apply changes.

## License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/g0ldyy/comet/blob/main/LICENSE) file for details.

---

Created with ❤️ by [Goldy](https://github.com/g0ldyy).
