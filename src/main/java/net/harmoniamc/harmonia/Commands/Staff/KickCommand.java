package net.harmoniamc.harmonia.Commands.Staff;

import net.harmoniamc.harmonia.Commands.ErrorCodes;
import net.harmoniamc.harmoniaapi.command.Command;
import net.harmoniamc.harmoniaapi.command.paramter.Param;
import net.harmoniamc.harmoniaapi.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KickCommand {

    @Command(names = {"kick", "bonk"}, permission = "staff.kick", description = "", playerOnly = true)
    public void KickCommand(Player sender, @Param(name = "player", required = true) Player target, @Param(name = "reason", required = true) String reason){
        target.kickPlayer("§cKicked by a moderator. \n§8Error code: " +  + ErrorCodes.KICKED_BY_STAFF.getErrorCode());
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player.hasPermission("staff.kick")) { player.sendMessage(sender.getDisplayName() + " §chas kicked " + target.getDisplayName() + " §7(Reason: " + reason + ")"); }
        }
    }
}
