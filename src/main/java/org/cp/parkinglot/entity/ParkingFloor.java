package org.cp.parkinglot.entity;

import org.cp.parkinglot.exception.ParkingException;
import org.cp.parkinglot.exception.VehicleException;
import org.cp.parkinglot.util.AutoIncrementIdGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingFloor {

    int parkingFloorId;
    int currentFloor;
    List<ParkingSlots> parkingSlots;
    int capacity;

    public ParkingFloor(int currentFloor, int capacity,List<ParkingSlots> parkingSlots) {
        if(capacity<=0) throw new ParkingException("capacity cannot be less than or 0");
        this.parkingFloorId = AutoIncrementIdGenerator.incrementAndGetParkingFloorIdCounter();
        this.currentFloor = currentFloor;
        if(parkingSlots.size()<capacity)
            this.parkingSlots = parkingSlots;
            else throw new VehicleException("Out of Capacity");
        this.capacity = capacity;
    }

    public int getParkingFloorId() {
        return parkingFloorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public List<ParkingSlots> getParkingSlots() {
        return Collections.unmodifiableList(this.parkingSlots);
    }

    public boolean isFull() {
        return this.parkingSlots.size()>=capacity;
    }

    public List<ParkingSlots> getAvailableSlots() {
        if(isFull()) return Collections.emptyList();
       return this.parkingSlots.stream().filter(slots-> !slots.isParked()).collect(Collectors.toList());
    }
}
