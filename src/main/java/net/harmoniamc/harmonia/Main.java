package net.harmoniamc.harmonia;

import net.harmoniamc.harmonia.Commands.Staff.DisplayRanks;
import net.harmoniamc.harmonia.Commands.Staff.KickCommand;
import net.harmoniamc.harmonia.Database.DatabaseUtils.Database;
import net.harmoniamc.harmonia.Database.Infos.PlayerInfos;
import net.harmoniamc.harmonia.Database.Listeners.PlayerJoin;
import net.harmoniamc.harmonia.ModerationCore.BanManager;
import net.harmoniamc.harmoniaapi.command.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    public Database database = new Database();
    public PlayerInfos playerInfos = new PlayerInfos();
    public BanManager banManager = new BanManager();

    @Override
    public void onEnable() {
        instance = this;
        CommandHandler.registerCommands(KickCommand.class, this);
        CommandHandler.registerCommands(DisplayRanks.class, this);

        //Register listeners
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);

        //Connects to the mysql database
        database.connect("localhost", 3306, "harmonia", "root", "");
        banManager.ban(playerInfos.getUUID("PuedoYT"), 15000, "Cheating");
    }

    @Override
    public void onDisable() {
        database.disconnect();
    }

    /**
     * Récupérer cette class dans les autres
     * @return Harmonia
     */
    public static Main getInstance() {
        return instance;
    }
}
