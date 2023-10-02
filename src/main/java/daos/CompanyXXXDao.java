package daos;

import business.Company;
import exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyXXXDao extends Dao implements CompanyDAOInterface {    
    /*
     * Assume that symbol is unique
     *
     * Complete this method
     */
    @Override
    public Company findBySymbol(String symbol) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Company c = null;

        return c;
    }

    /*
     * Complete this method
     */
    @Override
    public List<Company> findCompaniesShareGreaterThan(double share) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Company> companies = new ArrayList<Company>();

        return companies;		// companies could be empty
    }
    
    @Override
    public List<Company> findAllCompanies() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Company> companies = new ArrayList<Company>();
        try {
            con = getConnection();

            String query = "SELECT * FROM COMPANYxxx";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                int companyId = rs.getInt("COMPANYID");
                String symbol = rs.getString("SYMBOL");
                String companyName = rs.getString("COMPANYNAME");
                double sharePrice = rs.getDouble("SHAREPRICE");
                double high = rs.getDouble("HIGH");
                double low = rs.getDouble("LOW");

                Company c = new Company(companyId, symbol, companyName, sharePrice, high, low);
                companies.add(c);
            }
        } catch (SQLException e) {
            throw new DaoException("CompanyXXXDao findAllCompanies(): " + e.getMessage());
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
                throw new DaoException("CompanyXXXDao findAllCompanies(): " + e.getMessage());
            }
        }
        return companies;		// companies could be empty
    }

    @Override
    public Company findByPrimaryKey(int id) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Company c = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM COMPANYxxx WHERE COMPANYID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                int companyId = rs.getInt("COMPANYID");
                String symbol = rs.getString("SYMBOL");
                String companyName = rs.getString("COMPANYNAME");
                double sharePrice = rs.getDouble("SHAREPRICE");
                double high = rs.getDouble("HIGH");
                double low = rs.getDouble("LOW");

                c = new Company(companyId, symbol, companyName, sharePrice, high, low);
            }

        } catch (SQLException e) {
            throw new DaoException("CompanyXXXDao findByPrimaryKey(): " + e.getMessage());
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
                throw new DaoException("CompanyXXXDao findByPrimaryKey(): " + e.getMessage());
            }
        }
        return c;
    }

    /*
     * Example where primary key is NOT inserted automatically by DBMS
     */
    @Override
    public int addCompany(Company c) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        try {
            con = getConnection();
            /*
             * Ensure symbol does not already exist
             */
            String query = "SELECT SYMBOL FROM COMPANYxxx WHERE SYMBOL = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, c.getSymbol());

            rs = ps.executeQuery();
            if (rs.next()) {
                throw new DaoException("Symbol " + c.getSymbol() + " already exists");
            }

            //String command = "INSERT INTO COMPANYxxx (COMPANYID, SYMBOL, COMPANYNAME, SHAREPRICE, HIGH, LOW) VALUES(?, ?, ?, ?, ?, ?)";
            // As all fields are being inserted, it is not necessary to name the fields
            String command = "INSERT INTO COMPANYxxx VALUES(?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(command);
            ps.setInt(1, c.getCompanyId());
            ps.setString(2, c.getSymbol());
            ps.setString(3, c.getCompanyName());
            ps.setDouble(4, c.getSharePrice());
            ps.setDouble(5, c.getHigh());
            ps.setDouble(6, c.getLow());

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("CompanyXXXDao addCompany(): " + e.getMessage());
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
                throw new DaoException("CompanyXXXDao addCompany(): " + e.getMessage());
            }
        }
        return rowsAffected;
    }

    @Override
    public int amendCompany(Company c) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        try {
            con = getConnection();
            // Assume symbol is unique in database
            // Ensure the new symbol does not belong to another company
            String query = "SELECT COMPANYID, SYMBOL FROM COMPANYxxx WHERE SYMBOL = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, c.getSymbol());

            rs = ps.executeQuery();
            if (rs.next()) {
                int compId = rs.getInt("COMPANYID");
                if (compId != c.getCompanyId()) {
                    throw new DaoException("Symbol " + c.getSymbol() + " already exists for another company");
                }
            }

            String command = "UPDATE COMPANYxxx SET SYMBOL=?, COMPANYNAME=?, SHAREPRICE=?, HIGH=?, LOW=? WHERE COMPANYID=?";
            ps = con.prepareStatement(command);
            ps.setString(1, c.getSymbol());
            ps.setString(2, c.getCompanyName());
            ps.setDouble(3, c.getSharePrice());
            ps.setDouble(4, c.getHigh());
            ps.setDouble(5, c.getLow());
            ps.setInt(6, c.getCompanyId());

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("CompanyXXXDao amendCompany: " + e.getMessage());
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
                throw new DaoException("CompanyXXXDao amendCompany(): " + e.getMessage());
            }
        }
        return rowsAffected;
    }

    @Override
    public int deleteCompany(int companyId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        try {
            con = getConnection();

            String command = "DELETE FROM COMPANYxxx WHERE COMPANYID=?";
            ps = con.prepareStatement(command);
            ps.setInt(1, companyId);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("CompanyXXXDao deleteCompany: " + e.getMessage());
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
                throw new DaoException("CompanyXXXDao deleteCompany(): " + e.getMessage());
            }
        }
        return rowsAffected;
    }

    public static void main(String[] args) {
        try {
            CompanyDAOInterface dao = new CompanyXXXDao();
            List<Company> companies = dao.findAllCompanies();

            if (companies.isEmpty()) {
                System.out.println("List is empty");
            } else {
                for (Company c : companies) {
                    System.out.println(c);
                }
            }
        } catch (DaoException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

}
