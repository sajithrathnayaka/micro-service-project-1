package com.studentmanagementsystem.vehicleservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleSavedEvent {
    private String vehicleNumber;
}

