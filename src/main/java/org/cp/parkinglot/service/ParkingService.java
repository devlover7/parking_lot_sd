package org.cp.parkinglot.service;

import org.cp.parkinglot.entity.ParkingSlots;
import org.cp.parkinglot.entity.User;
import org.cp.parkinglot.entity.Vehicle;

import java.util.List;

public interface ParkingService {

    public void createParkingSlots(int capacity);
    void createParkingSpace(int capacity, List<ParkingSlots> parkingSlotsList);
    Integer parkVehicle (User user, Vehicle vehicle);
}
