package org.cp.parkinglot.entity;

import org.cp.parkinglot.entity.enums.VehicleType;

import static org.cp.parkinglot.util.AutoIncrementIdGenerator.incrementAndGetParkingSlotIdCounter;

public class ParkingSlots {

 private int parkingId;
    private   boolean isParked;
    private   VehicleType vehicleType;
    private   User owner;
    private   Vehicle vehicle;

    public ParkingSlots(VehicleType vehicleType) {
        this.parkingId = incrementAndGetParkingSlotIdCounter();
        this.isParked = false;
        this.vehicleType = vehicleType;
    }

    public int getParkingId() {
        return parkingId;
    }

    public boolean isParked() {
        return isParked;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public User getOwner() {
        return owner;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        if (vehicle != null && !vehicle.getVehicleType().equals(this.vehicleType)) {
            throw new IllegalArgumentException("Vehicle type does not match the parking slot type.");
        }
        this.vehicle = vehicle;
        this.isParked = vehicle != null;
    }


}
