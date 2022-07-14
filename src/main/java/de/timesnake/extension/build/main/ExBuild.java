package de.timesnake.extension.build.main;

import de.timesnake.basic.bukkit.util.Server;
import de.timesnake.database.util.Database;
import de.timesnake.extension.build.chat.Plugin;
import de.timesnake.extension.build.cmd.PvPCmd;
import de.timesnake.extension.build.game.MapCmd;
import de.timesnake.extension.build.server.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ExBuild extends JavaPlugin {

    public static ExBuild getPlugin() {
        return plugin;
    }

    private static ExBuild plugin;

    public void onEnable() {
        plugin = this;

        Server.getCommandManager().addCommand(this, "map",
                new MapCmd(Database.getNetwork().getNetworkFile("templates").getFile()), Plugin.BUILD);
        Server.getCommandManager().addCommand(this, "pvp", new PvPCmd(), Plugin.BUKKIT);

        new EventManager();
    }
}
