/*
 * extension-build.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.extension.build.cmd;

import de.timesnake.basic.bukkit.util.Server;
import de.timesnake.basic.bukkit.util.chat.Argument;
import de.timesnake.basic.bukkit.util.chat.CommandListener;
import de.timesnake.basic.bukkit.util.chat.Sender;
import de.timesnake.basic.bukkit.util.world.ExWorld;
import de.timesnake.library.basic.util.chat.ExTextColor;
import de.timesnake.library.extension.util.chat.Code;
import de.timesnake.library.extension.util.chat.Plugin;
import de.timesnake.library.extension.util.cmd.Arguments;
import de.timesnake.library.extension.util.cmd.ExCommand;
import net.kyori.adventure.text.Component;

import java.util.List;

public class PvPCmd implements CommandListener {

    private Code.Permission pvpPerm;

    @Override
    public void onCommand(Sender sender, ExCommand<Sender, Argument> cmd, Arguments<Argument> args) {
        if (!sender.hasPermission(this.pvpPerm)) {
            return;
        }

        if (args.isLengthEquals(1, false)) {
            if (!args.get(0).isWorldName(true)) {
                return;
            }

            ExWorld world = args.get(0).toWorld();
            world.setPVP(!world.getPVP());

            sender.sendPluginMessage(Component.text("PvP " + (world.getPVP() ? "enabled" : "disabled"), ExTextColor.PERSONAL));
            return;
        }

        if (!sender.isPlayer(true)) {
            return;
        }

        ExWorld world = sender.getUser().getExWorld();
        world.setPVP(!world.getPVP());

        sender.sendPluginMessage(Component.text("PvP " + (world.getPVP() ? "enabled" : "disabled"), ExTextColor.PERSONAL));
    }

    @Override
    public List<String> getTabCompletion(ExCommand<Sender, Argument> cmd, Arguments<Argument> args) {
        if (args.length() == 1) {
            return Server.getCommandManager().getTabCompleter().getWorldNames();
        }
        return List.of();
    }

    @Override
    public void loadCodes(Plugin plugin) {
        this.pvpPerm = plugin.createPermssionCode("pvp", "exbuild.pvp");
    }
}
