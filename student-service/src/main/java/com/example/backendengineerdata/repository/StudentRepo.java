package com.example.backendengineerdata.repository;

import com.example.backendengineerdata.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, String> {
    long count();
}

