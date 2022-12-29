package net.harmoniamc.harmonia.Database.Infos;

import net.harmoniamc.harmonia.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerInfos {

    private Main main = Main.getInstance();

    /**
     * Updates a player's infos in the database
     * @param player
     */
    public void update(Player player){
        try {
            PreparedStatement sts = Main.getInstance().database.getConnection().prepareStatement("SELECT username FROM player_infos WHERE uuid=?");
            sts.setString(1, player.getUniqueId().toString());

            ResultSet rs = sts.executeQuery();
            if(rs.next()){
                PreparedStatement update = main.getInstance().database.getConnection().prepareStatement("UPDATE player_infos SET username=? WHERE uuid=?");
                update.setString(1, player.getName());
                update.setString(2, player.getUniqueId().toString());
                update.executeUpdate();
                update.close();

                System.out.println("Player was updatde successfully!");
            } else {
                PreparedStatement insert = main.getInstance().database.getConnection().prepareStatement("INSERT INTO player_infos (uuid,username) VALUES (?,?)");
                insert.setString(1, player.getUniqueId().toString());
                insert.setString(2, player.getName());
                insert.executeUpdate();
                insert.close();

                System.out.println("Player was inserted into the DATABASE.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Vérifie si le joueur a des informations dans la table
     * @param playerName
     * @return true/false
     */
    public boolean exist(String playerName){
        try {
            PreparedStatement sts = Main.getInstance().database.getConnection().prepareStatement("SELECT * FROM player_infos WHERE username=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Gets a player's uuid
     * @param playerName
     * @return UUID
     **/
    public UUID getUUID(String playerName){
        if(!exist(playerName)){
            throw new NullPointerException("Player isn't registered in the database.");
        }

        try{
            PreparedStatement sts = main.getInstance().database.getConnection().prepareStatement("SELECT uuid FROM player_infos WHERE username=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();

            if(rs.next()) {
                return UUID.fromString(rs.getString("uuid"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        throw new NullPointerException("Player isn't registered in the database.");
    }
}
