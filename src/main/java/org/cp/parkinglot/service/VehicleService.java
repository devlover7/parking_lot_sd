package org.cp.parkinglot.service;

import org.cp.parkinglot.entity.Vehicle;
import org.cp.parkinglot.entity.enums.VehicleType;

public interface VehicleService {
    Vehicle createVehicle(long chasisNumber, VehicleType vehicleType);
}
