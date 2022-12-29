package net.harmoniamc.harmonia.ModerationCore;

import net.harmoniamc.harmonia.Commands.ErrorCodes;
import net.harmoniamc.harmonia.Main;
import net.harmoniamc.harmonia.Utils.AlphaNumericStringGenerator;
import net.harmoniamc.harmonia.Utils.TimeUnit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BanManager {

    private Main main = Main.getInstance();
    private AlphaNumericStringGenerator banid = new AlphaNumericStringGenerator();

    public void ban(UUID uuid, long endToSecs, String reason){
        if(isBanned(uuid)) return;

        long endToMilis = endToSecs * 1000;
        long end = endToMilis + System.currentTimeMillis();

        if(endToSecs == -1){
            end = -1;
        }

        try {
            PreparedStatement sts = Main.getInstance().database.getConnection().prepareStatement("INSERT INTO bans (ban_id, uuid, end, reason, active) VALUES (?,?,?,?,?)");
            sts.setString(1, banid.generateAlphaNumString());
            sts.setString(2, uuid.toString());
            sts.setLong(3, end);
            sts.setString(4, reason);
            sts.setBoolean(5, true);
            sts.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(Bukkit.getPlayer(uuid) != null){
            Player target = Bukkit.getPlayer(uuid);
            target.kickPlayer("§cAn error has occurred. \n§8Error code: " + ErrorCodes.KICKED_BY_SYSTEM.getErrorCode());
        }
    }

    public void unban(UUID uuid){
        if(!isBanned(uuid)) return;

        try {
            PreparedStatement sts = Main.getInstance().database.getConnection().prepareStatement("SELECT * FROM bans WHERE uuid=?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();

            if(rs.next()){
                boolean isactive = rs.getBoolean("active");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isBanned(UUID uuid){
        try {
            PreparedStatement sts = Main.getInstance().database.getConnection().prepareStatement("SELECT * FROM bans WHERE uuid=?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void checkDuration(UUID uuid){
        if(!isBanned(uuid)) return;

        if(getEnd(uuid) == -1) return;

        if(getEnd(uuid) < System.currentTimeMillis()){
            unban(uuid);
        }
    }

    public long getEnd(UUID uuid){
        if(!isBanned(uuid)) return 0;

        try {
            PreparedStatement sts = Main.getInstance().database.getConnection().prepareStatement("SELECT * FROM bans WHERE uuid=?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            if(rs.next()){
                return rs.getLong("end");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public String getTimeLeft(UUID uuid){
        if(!isBanned(uuid)) return "§cPlayer not banned.";

        if(getEnd(uuid) == -1) return "§cPermanent";

        long remainingTime = (getEnd(uuid) - System.currentTimeMillis()) / 1000;
        Date date = new Date(getEnd(uuid));
        SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy hha");
        System.out.println("Current date is " + ft.format(date));
        return ft.format(date);

    }

    public String getReason(UUID uuid){
        if(!isBanned(uuid)) return "§cPlayer not banned.";

        try {
            PreparedStatement sts = Main.getInstance().database.getConnection().prepareStatement("SELECT * FROM bans WHERE uuid=?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            if(rs.next()){
                return rs.getString("reason");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "§cPlayer not banned.";
    }

    public String getBanID(UUID uuid){
        if(!isBanned(uuid)) return "§cPlayer not banned.";

        try {
            PreparedStatement sts = Main.getInstance().database.getConnection().prepareStatement("SELECT * FROM bans WHERE uuid=?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            if(rs.next()){
                return rs.getString("ban_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "§cPlayer not banned.";
    }

}
