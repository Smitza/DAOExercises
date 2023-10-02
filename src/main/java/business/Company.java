package business;

import java.io.Serializable;

public class Company implements Serializable {

    private int companyId;
    private String symbol;
    private String companyName;
    private double sharePrice;
    private double high;
    private double low;

    public Company() {
        companyId = 0;
        symbol = "";
        companyName = "";
        sharePrice = 0.0;
        high = 0.0;
        low = 0.0;
    }

    public Company(int companyId, String symbol, String companyName, double sharePrice, double high, double low) {
        this.companyId = companyId;
        this.symbol = symbol;
        this.companyName = companyName;
        this.sharePrice = sharePrice;
        this.high = high;
        this.low = low;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Company other = (Company) obj;
        if (this.companyId != other.companyId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.companyId;
        return hash;
    }

    @Override
    public String toString() {
        return "Company{" + "companyId=" + companyId + " symbol=" + symbol + " companyName=" + companyName + " sharePrice=" + sharePrice + " high=" + high + " low=" + low + '}';
    }
    /*
     * Useful for testing
     */
    public void display() {
        System.out.printf("%10d  %-10s%-20s%10.2f%10.2f%10.2f\n",
            companyId, symbol, companyName, sharePrice, high, low);
    }

    public static void main(String[] args) {
        Company c1 = new Company() ;
        System.out.println("c1 = " + c1);
        c1.setCompanyId(1);
        c1.setSymbol("AAA");
        c1.setCompanyName("AAAA");
        c1.setSharePrice(10.0);
        c1.setHigh(20.0);
        c1.setLow(5.0);
        System.out.println("c1 = " + c1);
        Company c2 = new Company(2, "BBB", "BBBB", 15.5, 17.5, 12.5);
        System.out.println("c2 = " + c2);
        System.out.println("id " + c2.getCompanyId());
        System.out.println("symbol " + c2.getSymbol());
        System.out.println("name " + c2.getCompanyName());
        System.out.println("sharePrice " + c2.getSharePrice());
        System.out.println("high " + c2.getHigh());
        System.out.println("low " + c2.getLow());

        c2.display();

    }
}
