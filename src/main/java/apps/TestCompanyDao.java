package apps;

import java.util.List;
import business.Company;
import daos.CompanyDao;
import daos.CompanyXXXDao;
import daos.CompanyDAOInterface;
import exceptions.DaoException;

public class TestCompanyDao {

    public static void main(String[] args) {
        try {
            TestCompanyDao t = new TestCompanyDao();
            t.testFindAllCompanies();
            t.testFindByPrimaryKey();
            //t.testFindBySymbol();
            //t.testFindCompaniesShareGreaterThan();
            t.testAddCompany();
            t.testAddCompanyXXX();
            //t.testAmendCompany();
            //t.testDeleteCompany();
        } catch (DaoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    public void testFindAllCompanies() throws DaoException {
        System.out.println("testFindAllCompanies()");
        try {
            CompanyDAOInterface dao = new CompanyDao();

            List<Company> companies = dao.findAllCompanies();
            if (companies.isEmpty()) {
                System.out.println("List is empty");
            } else {
                for (Company c : companies) {
                    c.display();
                }
            }
        } catch (DaoException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void testFindByPrimaryKey() throws DaoException {
        System.out.println("testFindByPrimaryKey()");
        try {
            CompanyDAOInterface dao = new CompanyDao();
            int id = 1;
            Company c = dao.findByPrimaryKey(id);
            if (c == null) {
                System.out.println("No company with id = " + id);
            } else {
                c.display();
            }

            id = 3;
            c = dao.findByPrimaryKey(id);
            if (c == null) {
                System.out.println("No company with id = " + id);
            } else {
                c.display();
            }

            id = 333;
            c = dao.findByPrimaryKey(id);
            if (c == null) {
                System.out.println("No company with id = " + id);
            } else {
                c.display();
            }

        } catch (DaoException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void testFindBySymbol() throws DaoException {
        System.out.println("testFindBySymbol()");
        
    }

    public void testFindCompaniesShareGreaterThan() throws DaoException {
        System.out.println("testFindCompaniesShareGreaterThan()");
        
    }

    public void testAddCompany() throws DaoException {
        System.out.println("testAddCompany()");
        try {
            CompanyDAOInterface dao = new CompanyDao();

            Company c = new Company(-1, "Audi", "Audi Motors", 10.5, 14.7, 8.2);

            int rowsAffected = dao.addCompany(c);
            System.out.println("rowsAffected = " + rowsAffected);
            System.out.println("Now display companies");
            dao.findAllCompanies();

            System.out.println("Now attempt to add company with same symbol");

            c = new Company(-1, "Audi", "Audi Motors", 10.5, 14.7, 8.2);

            rowsAffected = dao.addCompany(c);
            System.out.println("rowsAffected = " + rowsAffected);

        } catch (DaoException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void testAddCompanyXXX() throws DaoException {
        System.out.println("testAddCompanyXXX()");
        try {
            CompanyDAOInterface dao = new CompanyXXXDao();

            // must supply new PK.  Not very good idea!
            Company c = new Company(10010, "Audi", "Audi Motors", 10.5, 14.7, 8.2);

            int rowsAffected = dao.addCompany(c);
            System.out.println("rowsAffected = " + rowsAffected);

            System.out.println("Now attempt to add company with same symbol");

            c = new Company(10011, "Audi", "Audi Motors", 10.5, 14.7, 8.2);

            rowsAffected = dao.addCompany(c);
            System.out.println("rowsAffected = " + rowsAffected);

        } catch (DaoException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void testAmendCompany() throws DaoException {
        System.out.println("testAmendCompany()");
        System.out.println("Ensure you complete findBySymbol() method first");
        try {
            CompanyDAOInterface dao = new CompanyDao();

            String symbol = "Audi";
            Company c = dao.findBySymbol(symbol);
            if (c == null) {
                System.out.println("No such company " + symbol);
            } else {
                c.setCompanyName("xxxx");
                c.setSharePrice(100.0);
                c.setHigh(110.0);
                c.setLow(1.0);
                int rowsAffected = dao.amendCompany(c);
                System.out.println("rowsAffected = " + rowsAffected);
                System.out.println("Now display companies");
                dao.findAllCompanies();

                System.out.println("Now attempt to amend company with another company's symbol");

                c.setSymbol("AIB");

                rowsAffected = dao.amendCompany(c);
                System.out.println("rowsAffected = " + rowsAffected);
                dao.findAllCompanies();
            }

        } catch (DaoException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void testDeleteCompany() throws DaoException {
        System.out.println("testDeleteCompany()");
        System.out.println("Ensure you complete findBySymbol() method first");
        try {
            CompanyDAOInterface dao = new CompanyDao();

            Company c = dao.findBySymbol("Audi");
            int rowsAffected = dao.deleteCompany(c.getCompanyId());
            System.out.println("rowsAffected = " + rowsAffected);

        } catch (DaoException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}