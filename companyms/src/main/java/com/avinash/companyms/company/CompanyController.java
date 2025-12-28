package com.avinash.companyms.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
        boolean companyUpdated = companyService.updateCompany(company,id);
        if(companyUpdated)return new ResponseEntity<>("Company Updated Successfully!!", HttpStatus.OK);
        return new ResponseEntity<>("Company Not Found with id: "+id,HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company added Successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
       if(companyService.deleteCompanyById(id))
           return new ResponseEntity<>("Company with id: "+id+" deleted Successfully",HttpStatus.OK);
       return new ResponseEntity<>("Company with id: "+id+" not Found!!",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Optional<Company> company = companyService.getCompanyById(id);
        if(company.isPresent()) {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
