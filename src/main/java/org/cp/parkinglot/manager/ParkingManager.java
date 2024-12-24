package org.cp.parkinglot.manager;

import org.cp.parkinglot.service.ParkingService;
import org.cp.parkinglot.service.UserService;
import org.cp.parkinglot.service.VehicleService;

public class ParkingManager {

    UserService userService;
    VehicleService vehicleService;
    ParkingService parkingService;

    public ParkingManager(UserService userService, VehicleService vehicleService, ParkingService parkingService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.parkingService = parkingService;
    }

    public UserService getUserService() {
        return userService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public ParkingService getParkingService() {
        return parkingService;
    }
}
