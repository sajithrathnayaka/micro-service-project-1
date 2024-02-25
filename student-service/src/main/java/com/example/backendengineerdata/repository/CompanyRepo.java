package com.example.backendengineerdata.repository;

import com.example.backendengineerdata.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Integer> {


}
