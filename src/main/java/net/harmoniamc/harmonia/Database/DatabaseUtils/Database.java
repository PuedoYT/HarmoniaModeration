package net.harmoniamc.harmonia.Database.DatabaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection conn;

    public void connect(String host, int port, String database, String user, String password){
        if(!isConnected()){
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
                System.out.println("[BanSystem] Connexion etablie avec la bdd");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect(){
        if(isConnected()){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected(){
        try {
            if((conn == null) || (conn.isClosed()) || conn.isValid(5)){
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Récupérer la connection
     * @return Connection
     */
    public Connection getConnection() {
        return conn;
    }
}
