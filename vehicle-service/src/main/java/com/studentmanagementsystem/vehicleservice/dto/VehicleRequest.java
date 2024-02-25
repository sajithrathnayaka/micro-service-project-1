package com.studentmanagementsystem.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleRequest {
    private String namePlate;
    private String category;
    private String brand;
    private String model;
    private int year;
}
