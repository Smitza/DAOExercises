package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Player implements Serializable
{

    private int playerId;
    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private List<Game> games = new ArrayList<Game>();

    public Player() {
        playerId = 0;
        username = "";
        password = "";
        lastname = "";
        firstname = "";
    }

    public Player(int playerId, String username, String password, String lastname, String firstname) {
        this.playerId = playerId;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.playerId != other.playerId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.playerId;
        return hash;
    }

    @Override
    public String toString() {
        return "Player{" + "playerId=" + playerId + "username=" + username + "password=" + password + "lastname=" + lastname + "firstname=" + firstname + '}';
    }

    public void display(){
        System.out.printf("%10d  %-10s%-10s%-10s%-10s\n",
            playerId, username, password, lastname, firstname);
    }

    public static void main(String[] args) {
        GregorianCalendar cal = new GregorianCalendar();
        long ms = cal.getTimeInMillis();
        java.sql.Date today = new java.sql.Date(ms);
        List<Game> games = new ArrayList<Game>();
        games.add(new Game(1, 100.0, today));
        games.add(new Game(2, 200.0, today));
        games.add(new Game(3, 300.0, today));

        Player p = new Player(1, "aceman", "aaa111", "Smith", "John");
        p.display();
        p.setGames(games);
        List<Game> listGames = p.getGames();
        for (Game g : listGames) {
            g.display();
        }

    }
    
}