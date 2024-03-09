package com.studentmanagementsystem.vehicleservice.controller;

import com.studentmanagementsystem.vehicleservice.dto.StandardResponse;
import com.studentmanagementsystem.vehicleservice.dto.VehicleRequest;
import com.studentmanagementsystem.vehicleservice.dto.VehicleResponse;
import com.studentmanagementsystem.vehicleservice.repository.VehicleRepo;
import com.studentmanagementsystem.vehicleservice.service.VehicleService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "student", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "student")
    @Retry(name = "student")
    public CompletableFuture<String> saveVehicle(@RequestBody VehicleRequest vehicleRequest) {
        //vehicleRequest.setStudentIndex(studentIndex);
        //vehicleService.saveVehicle(vehicleRequest);
        return CompletableFuture.supplyAsync(() -> vehicleService.saveVehicle(vehicleRequest));

    }
    public CompletableFuture<String> fallbackMethod(VehicleRequest vehicleRequest, RuntimeException runtimeException) {
        log.info("Cannot Place Order Executing Fallback logic");
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time!");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleResponse> getAllVehicle() {
        return vehicleService.getAllVehicles();
    }

}
