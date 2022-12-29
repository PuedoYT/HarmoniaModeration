package net.harmoniamc.harmonia.Commands.Staff;

import net.harmoniamc.harmonia.Commands.ErrorCodes;
import net.harmoniamc.harmonia.ModerationCore.BanManager;
import net.harmoniamc.harmoniaapi.command.Command;
import net.harmoniamc.harmoniaapi.command.paramter.Param;
import net.harmoniamc.harmoniaapi.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BanCommand {

    @Command(names = {"ban", "megabonk"}, permission = "staff.kick", description = "", playerOnly = true)
    public void BanCommand(Player sender, @Param(name = "player", required = true) Player target, @Param(name = "reason", required = true) String reason){
        BanManager bm = new BanManager();


    }
}

