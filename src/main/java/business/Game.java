package business;

import java.io.Serializable;
import java.sql.Date;
import java.util.GregorianCalendar;

public class Game implements Serializable {

    private int gameId;
    private double balance;
    private java.sql.Date period;

    public Game() {
        gameId = 0;
        balance = 0.0;
        period = new Date(0L);
    }

    public Game(int gameId, double balance, Date period) {
        this.gameId = gameId;
        this.balance = balance;
        this.period = period;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.gameId;
        return hash;
    }

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + " balance=" + balance + " period=" + period + '}';
    }

    /*
     * Useful for testing
     */
    public void display(){
        System.out.printf("%10d%10.2f%15s\n", gameId, balance, period);
    }

    public static void main(String[] args) {

        System.out.println("java.util.Date, 2 constructors");
        java.util.Date utilDate1 = new java.util.Date();  // today's date
        System.out.println("utilDate1 = " + utilDate1);
        java.util.Date utilDate2 = new java.util.Date(0L);
        System.out.println("utilDate2 = " + utilDate2);

        System.out.println("java.sql.Date, 1 constructor");
        java.sql.Date sqlDate1 = new java.sql.Date(0L) ;
        System.out.println("sqllDate1 = " + sqlDate1);

        System.out.println("GregorianCalendar");
        GregorianCalendar gregCal = new GregorianCalendar();
        System.out.println("gregCal = " + gregCal);
        System.out.println("Use GregorianCalendar to set sqlDate1, utilDate2 to today");
        long ms = gregCal.getTimeInMillis();
        System.out.println("ms = " + ms);
        sqlDate1.setTime(ms);
        System.out.println("sqlDate1 =  " + sqlDate1);
        utilDate2.setTime(ms);
        System.out.println("utilDate2 = " + utilDate2);
        
        System.out.println("Test Game");

        Game g1 = new Game();
        System.out.println("g1 = " + g1);
        g1.setGameId(1);
        g1.setBalance(1000.0);
        GregorianCalendar cal = new GregorianCalendar();
        ms = cal.getTimeInMillis();
        java.sql.Date today = new java.sql.Date(ms);
        g1.setPeriod(today);
        System.out.println("g1 = " + g1);

        Game g2 = new Game(2, 2000.0, today);
        System.out.println("g2 = " + g2);

        g2.display();

    }
}