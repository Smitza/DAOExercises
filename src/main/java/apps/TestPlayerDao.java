package apps;

import java.util.List;
import business.Player;
import daos.PlayerDao;
import exceptions.DaoException;

public class TestPlayerDao {

    public static void main(String[] args) {
        try {
            TestPlayerDao t = new TestPlayerDao();
            //t.testFindAllPlayers();
            t.testFindPlayerByUsernamePassword();
        } catch (DaoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    public void testFindAllPlayers() throws DaoException {
        System.out.println("testFindAllPlayers()");
        try {
            PlayerDao dao = new PlayerDao();

            List<Player> players = dao.findAllPlayers();
            if (players.isEmpty()) {
                System.out.println("List is empty");
            } else {
                for (Player p : players) {
                    p.display();
                }
            }
        } catch (DaoException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void testFindPlayerByUsernamePassword() throws DaoException {
        System.out.println("testFindPlayerByUsernamePassword()");
        try {
            PlayerDao dao = new PlayerDao();

            String username = "ccc333";
            String password = "ccc333";
            Player p = dao.findPlayerByUsernamePassword(username, password);
            if (p == null) {
                System.out.println("No such player " + username + " " + password);
            } else {
                p.display();
            }

            username = "ccc333";
            password = "ccc222";
            p = dao.findPlayerByUsernamePassword(username, password);
            if (p == null) {
                System.out.println("No such player " + username + " " + password);
            } else {
                p.display();
            }
            // SQL Injection
            System.out.println("Example of SQL Injection");
            username = "ccc333' OR 'a' = 'b" ;
            password = "xxxxxxxxxx";
            p = dao.findPlayerByUsernamePassword(username, password);
            if (p == null) {
                System.out.println("No such player " + username + " " + password);
            } else {
                p.display();
            }
        } catch (DaoException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}