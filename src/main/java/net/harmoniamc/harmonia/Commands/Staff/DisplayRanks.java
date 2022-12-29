package net.harmoniamc.harmonia.Commands.Staff;

import net.harmoniamc.harmonia.Commands.ErrorCodes;
import net.harmoniamc.harmoniaapi.command.Command;
import net.harmoniamc.harmoniaapi.command.paramter.Param;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DisplayRanks {

    @Command(names = {"showranks"}, permission = "admin.show.staff_ranks", description = "", playerOnly = true)
    public void DisplayRanks(Player sender){
        sender.sendMessage(
                "§8------------------"
                + "\n§4Owner " + sender.getName() + "§b: Hello"
                + "\n§4Admin " + sender.getName() + "§b: Hello"
                + "\n§bDeveloper " + sender.getName() + "§f: Hello"
                + "\n§cSrModerator " + sender.getName() + "§f: Hello"
                + "\n§cModerator " + sender.getName() + "§f: Hello"
                + "\n§cHelper " + sender.getName() + "§f: Hello"
                + "\n§dBuilder " + sender.getName() + "§f: Hello"
                + "\n§5Partner " + sender.getName() + "§f: Hello"
                + "\n§9Media " + sender.getName() + "§f: Hello"
                + "\n§6Harmonia+ " + sender.getName() + "§f: Hello"
                + "\n§aVip+ " + sender.getName() + "§f: Hello"
                + "\n§aVip " + sender.getName() + "§f: Hello"
                + "\n§7" + sender.getName() + "§f: Hello"
        );
    }
}
