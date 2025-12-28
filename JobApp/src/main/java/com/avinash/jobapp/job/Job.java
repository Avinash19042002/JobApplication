package com.avinash.jobapp.job;

import com.avinash.jobapp.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    @ManyToOne
    @JoinColumn(name="company_id") //Fk column  Equivalent SQL : company_id BIGINT REFERENCES company(id)
    private Company company;

}
