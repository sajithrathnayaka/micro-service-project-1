package com.studentmanagementsystem.vehicleservice.repository;

import com.studentmanagementsystem.vehicleservice.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepo extends MongoRepository<Vehicle, String> {

}
