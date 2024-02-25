package com.studentmanagementsystem.vehicleservice.controller;

import com.studentmanagementsystem.vehicleservice.dto.VehicleRequest;
import com.studentmanagementsystem.vehicleservice.dto.VehicleResponse;
import com.studentmanagementsystem.vehicleservice.repository.VehicleRepo;
import com.studentmanagementsystem.vehicleservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping("/{studentIndex}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveVehicle(@RequestBody VehicleRequest vehicleRequest, @PathVariable String studentIndex) {


        vehicleService.saveVehicle(vehicleRequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleResponse> getAllVehicle() {
        return vehicleService.getAllVehicles();
    }

}
