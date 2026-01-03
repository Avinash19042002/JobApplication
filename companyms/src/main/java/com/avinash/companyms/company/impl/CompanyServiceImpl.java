package com.avinash.companyms.company.impl;

import com.avinash.companyms.company.CompanyRepository;
import com.avinash.companyms.company.CompanyService;
import com.avinash.companyms.company.Company;

import com.avinash.companyms.company.clients.ReviewClient;
import com.avinash.companyms.company.dto.ReviewMessage;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    ReviewClient reviewClient;

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

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());
        /*
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).get();

        if(company!=null){
            double averageRating = company.getAverageRating();
            double totalRating = company.getTotalRating()+reviewMessage.getRating();
            int ratingCount = company.getRatingCounts()+1;
            company.setAverageRating(totalRating/(ratingCount*1.0));
            company.setTotalRating(totalRating);
            company.setRatingCounts(ratingCount);
            companyRepository.save(company);
        }
         */
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).get();
        if(company==null){
            System.out.println("Company with companyId "+reviewMessage.getCompanyId()+" not found. Skipping rating update.");
            return;
        }
        double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
        company.setRating(averageRating);
        companyRepository.save(company);
    }
}
