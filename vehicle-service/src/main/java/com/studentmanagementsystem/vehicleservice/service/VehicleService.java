package com.studentmanagementsystem.vehicleservice.service;

import com.studentmanagementsystem.vehicleservice.dto.StandardResponse;
import com.studentmanagementsystem.vehicleservice.dto.VehicleRequest;
import com.studentmanagementsystem.vehicleservice.dto.VehicleResponse;
import com.studentmanagementsystem.vehicleservice.entity.Vehicle;
import com.studentmanagementsystem.vehicleservice.event.VehicleSavedEvent;
import com.studentmanagementsystem.vehicleservice.repository.VehicleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.Optional;
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
    private final Tracer tracer;
    private final KafkaTemplate<String,VehicleSavedEvent> kafkaTemplate;

    public String saveVehicle(VehicleRequest vehicleRequest) {
        String studentIndex = vehicleRequest.getStudentIndex();
        Span studentServiceLookup = tracer.nextSpan().name("studentServiceLookup");
        try(Tracer.SpanInScope spanInScope = tracer.withSpan(studentServiceLookup.start())) {
            StandardResponse standardResponseMono = webClientBuilder.build().get()
                    .uri("http://student-service/api/v1/students/{studentIndex}",studentIndex)
                    .retrieve()
                    .bodyToMono(StandardResponse.class)
                    .block();
            Optional<VehicleResponse> index = Optional.ofNullable(findByIndex(studentIndex));
            System.out.println(index);
            if (index.isPresent()) {
                throw new RuntimeException("this student vehicle is already saved");
            }
            System.out.println("student service run success");
            Vehicle vehicle = mapper.map(vehicleRequest, Vehicle.class);
            System.out.println("mapping success");
            vehicleRepo.save(vehicle);
            System.out.println("save suc");
            kafkaTemplate.send("notificationTopic", new VehicleSavedEvent(vehicle.getNamePlate()));

            log.info("vehicle {} is saved",vehicle.getNamePlate());
            return "Vehicle saved successfully";
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(e);
            return "cannot save !!!";
        }
        finally {
            studentServiceLookup.end();
        }
    }

    public VehicleResponse findByIndex(String index) {
        Optional<Vehicle> vehicle = vehicleRepo.findById(index);
        System.out.println("vehicle is "+vehicle);
        if (vehicle.isPresent()) {
            return mapper.map(vehicle, VehicleResponse.class);
        } else {
            return null;
        }
    }

    public List<VehicleResponse> getAllVehicles() {
        System.out.println("get all vehicles");
        List<Vehicle> vehicles = vehicleRepo.findAll();
        return vehicles.stream().map(vehicle -> mapper.map(vehicle, VehicleResponse.class)).collect(Collectors.toList());
    }
}
