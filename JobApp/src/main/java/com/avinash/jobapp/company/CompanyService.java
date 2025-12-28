package com.avinash.jobapp.company;

import java.util.List;
import java.util.Optional;

public interface CompanyService{
    List<Company> getAllCompanies();
    boolean updateCompany(Company company,Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);

    Optional<Company> getCompanyById(Long id);
}
