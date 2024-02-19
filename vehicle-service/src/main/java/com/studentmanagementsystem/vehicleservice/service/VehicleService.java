package com.studentmanagementsystem.vehicleservice.service;

import com.studentmanagementsystem.vehicleservice.dto.VehicleRequest;
import com.studentmanagementsystem.vehicleservice.entity.Vehicle;
import com.studentmanagementsystem.vehicleservice.repository.VehicleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepo vehicleRepo;
    private final ModelMapper mapper;

    public void saveVehicle(VehicleRequest vehicleRequest) {
        Vehicle vehicle = mapper.map(vehicleRequest, Vehicle.class);
        vehicleRepo.save(vehicle);

        log.info("vehicle {} is saved",vehicle.getNamePlate());
    }
}
