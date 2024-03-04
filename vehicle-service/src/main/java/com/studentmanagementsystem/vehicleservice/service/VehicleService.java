package com.studentmanagementsystem.vehicleservice.service;

import com.studentmanagementsystem.vehicleservice.dto.StandardResponse;
import com.studentmanagementsystem.vehicleservice.dto.VehicleRequest;
import com.studentmanagementsystem.vehicleservice.dto.VehicleResponse;
import com.studentmanagementsystem.vehicleservice.entity.Vehicle;
import com.studentmanagementsystem.vehicleservice.repository.VehicleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound;
import org.springframework.http.HttpStatus;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepo vehicleRepo;
    private final ModelMapper mapper;
    private final WebClient.Builder webClientBuilder;

    public String saveVehicle(VehicleRequest vehicleRequest) {
        String studentIndex = vehicleRequest.getStudentIndex();
        try {
            StandardResponse standardResponseMono = webClientBuilder.build().get()
                    .uri("http://student-service/api/v1/students/{studentIndex}",studentIndex)
                    .retrieve()
                    .bodyToMono(StandardResponse.class)
                    .block();
            Vehicle vehicle = mapper.map(vehicleRequest, Vehicle.class);
            vehicleRepo.save(vehicle);
            log.info("vehicle {} is saved",vehicle.getNamePlate());
            return "Vehicle saved successfully";
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(e);
            return "cannot save !!!";
        }

    }

    public List<VehicleResponse> getAllVehicles() {
        System.out.println("get all vehicles");
        List<Vehicle> vehicles = vehicleRepo.findAll();
        return vehicles.stream().map(vehicle -> mapper.map(vehicle, VehicleResponse.class)).collect(Collectors.toList());
    }
}
