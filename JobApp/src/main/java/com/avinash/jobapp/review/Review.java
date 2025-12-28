package com.avinash.jobapp.review;

import com.avinash.jobapp.company.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double rating;

    @JsonIgnore // added to child side this time
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
