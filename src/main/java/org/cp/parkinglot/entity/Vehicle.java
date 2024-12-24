package org.cp.parkinglot.entity;

import org.cp.parkinglot.entity.enums.VehicleType;
import org.cp.parkinglot.util.AutoIncrementIdGenerator;

import java.util.Objects;

public class Vehicle
{
    private int vehicleId;
    private long chasisNumber;
    private String RTORegistrationNum;
    private User owner;
    private boolean isParked;
    private VehicleType vehicleType;

    public Vehicle(long chasisNumber, VehicleType vehicleType) {
        this.vehicleId = AutoIncrementIdGenerator.incrementAndGetVehicleIdCounter();
        this.chasisNumber = chasisNumber;
        this.isParked = false;
        this.vehicleType = vehicleType;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public long getChasisNumber() {
        return chasisNumber;
    }

    public String getRTORegistrationNum() {
        return RTORegistrationNum;
    }

    public User getOwner() {
        return owner;
    }

    public boolean isParked() {
        return isParked;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleId == vehicle.vehicleId && chasisNumber == vehicle.chasisNumber;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setRTORegistrationNum(String RTORegistrationNum) {
        this.RTORegistrationNum = RTORegistrationNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, chasisNumber);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", chasisNumber=" + chasisNumber +
                ", RTORegistrationNum='" + RTORegistrationNum + '\'' +
                ", owner=" + owner +
                ", isParked=" + isParked +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
