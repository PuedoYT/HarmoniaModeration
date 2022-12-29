package net.harmoniamc.harmonia.Database.Listeners;

import net.harmoniamc.harmonia.Database.Infos.PlayerInfos;
import net.harmoniamc.harmonia.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        PlayerInfos playerInfos = new PlayerInfos();

        playerInfos.update(player);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        Player player = e.getPlayer();
        if(Main.getInstance().banManager.isBanned(player.getUniqueId())){
            e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            e.setKickMessage(
                    "§c§lTEMPORARY SUSPENSION\n" +
                    "\n" +
                    "§fDue to violations of our Rules which where reported by players and confirmed by a moderator, §f\nyour access to §6§lHARMONIA §fhas been temporarily suspended.\n" +
                    "§fYour access will be restored at " + Main.getInstance().banManager.getTimeLeft(player.getUniqueId()) +
                    "\n" +
                    "§7You may appeal this suspension at harmoniamc.net/appeals &7&o(Suspension ID: " + Main.getInstance().banManager.getBanID(player.getUniqueId()) + ")");
        }
    }
}
