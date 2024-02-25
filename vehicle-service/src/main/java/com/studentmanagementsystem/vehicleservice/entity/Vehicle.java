package com.studentmanagementsystem.vehicleservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Vehicle {
    @Id
    private String id;
    private String studentIndex;
    private String namePlate;
    private String category;
    private String brand;
    private String model;
    private int year;

}
