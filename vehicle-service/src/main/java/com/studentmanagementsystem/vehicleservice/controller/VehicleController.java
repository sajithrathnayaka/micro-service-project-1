package com.studentmanagementsystem.vehicleservice.controller;

import com.studentmanagementsystem.vehicleservice.dto.VehicleRequest;
import com.studentmanagementsystem.vehicleservice.repository.VehicleRepo;
import com.studentmanagementsystem.vehicleservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveVehicle(@RequestBody VehicleRequest vehicleRequest) {
        vehicleService.saveVehicle(vehicleRequest);
    }
}
