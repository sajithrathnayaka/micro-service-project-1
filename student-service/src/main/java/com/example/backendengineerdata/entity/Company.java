package com.example.backendengineerdata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company {
    @Id
    @Column(nullable = false, length = 100)
    int id;
    @Column(nullable = false, length = 100)
    String name;
    @Column(nullable = false, length = 100)
    String website;
    @Column(nullable = false)
    int employees;
    @Column(nullable = false, length = 100)
    String category;

    @OneToMany(mappedBy="company")
    private Set<Student> students;
}
