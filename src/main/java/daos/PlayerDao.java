package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import business.Player;
import exceptions.DaoException;

public class PlayerDao extends Dao {

    public List<Player> findAllPlayers() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Player> players = new ArrayList<Player>();
        try {
            con = this.getConnection();

            String query = "SELECT * FROM PLAYER";
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                int playerId = rs.getInt("PLAYERID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String lastname = rs.getString("LASTNAME");
                String firstname = rs.getString("FIRSTNAME");
                Player p = new Player(playerId, username, password, lastname, firstname);
                players.add(p);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllPlayers() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
        return players;     // may be empty
    }

    /*
     * This method is NOT susceptible to SQL Injection
     */
    public Player findPlayerByUsernamePassword(String uname, String pword) throws DaoException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Player p = null;
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM PLAYER WHERE USERNAME = ? AND PASSWORD = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, uname);
            ps.setString(2, pword);
            
            rs = ps.executeQuery();
            if (rs.next()) {
                int playerId = rs.getInt("PLAYERID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String lastname = rs.getString("LASTNAME");
                String firstname = rs.getString("FIRSTNAME");
                p = new Player(playerId, username, password, lastname, firstname);
            }
        } catch (SQLException e) {
            throw new DaoException("findPlayerByUsernamePassword " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findPlayerByUsernamePassword" + e.getMessage());
            }
        }
        return p;     // p may be null 
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            PlayerDao dao = new PlayerDao();
            System.out.println("Testing SQL Injection");
            System.out.print("Enter username, e.g. bbb222  ");
            String username = sc.nextLine();
            System.out.print("Enter password, e.g. bbb222  ");
            String password = sc.nextLine();
            Player p = dao.findPlayerByUsernamePassword(username, password);
            if (p == null)
                System.out.println("No player " + username + " " + password);
            else
                p.display();

            System.out.print("Enter username, e.g. bbb222' OR '5' = '7  ");
            username = sc.nextLine();
            System.out.print("Enter password, e.g. anything you like    ");
            password = sc.nextLine();
            p = dao.findPlayerByUsernamePassword(username, password);
            if (p == null)
                System.out.println("No player " + username + " " + password);
            else
                p.display();
        } catch (DaoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}