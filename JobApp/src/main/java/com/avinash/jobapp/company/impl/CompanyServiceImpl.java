package com.avinash.jobapp.company.impl;

import com.avinash.jobapp.company.Company;
import com.avinash.jobapp.company.CompanyRepository;
import com.avinash.jobapp.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company,Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isPresent()){
            Company companyUpdate = companyOptional.get();
            companyUpdate.setName(company.getName());
            companyUpdate.setDescription(company.getDescription());
            companyUpdate.setJobs(company.getJobs());
            companyRepository.save(companyUpdate);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
        companyRepository.deleteById(id);
        return true;
        }
        return false;
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }
}
