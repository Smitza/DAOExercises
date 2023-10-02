package daos;

import business.Company;
import exceptions.DaoException;
import java.util.List;

public interface CompanyDAOInterface {
    public List<Company> findAllCompanies() throws DaoException;
    public Company findByPrimaryKey(int id) throws DaoException;
    public Company findBySymbol(String symbol) throws DaoException;
    public List<Company> findCompaniesShareGreaterThan(double share) throws DaoException;
    public int addCompany(Company c) throws DaoException;
    public int amendCompany(Company c) throws DaoException;
    public int deleteCompany(int companyId) throws DaoException;    
}
