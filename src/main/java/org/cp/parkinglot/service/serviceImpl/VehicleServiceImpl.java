package org.cp.parkinglot.service.serviceImpl;

import org.cp.parkinglot.entity.Vehicle;
import org.cp.parkinglot.entity.enums.VehicleType;
import org.cp.parkinglot.service.VehicleService;

import java.util.ArrayList;
import java.util.List;

public class VehicleServiceImpl implements VehicleService {

    List<Vehicle> vehicleList = new ArrayList<>();

    @Override
    public Vehicle createVehicle(long chasisNumber, VehicleType vehicleType) {
        Vehicle newVehicle = new Vehicle(chasisNumber,vehicleType);
        vehicleList.add(newVehicle);
        return newVehicle;
    }
}
