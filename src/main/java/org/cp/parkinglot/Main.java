package org.cp.parkinglot;

import org.cp.parkinglot.entity.User;
import org.cp.parkinglot.entity.Vehicle;
import org.cp.parkinglot.entity.enums.VehicleType;
import org.cp.parkinglot.manager.ParkingManager;
import org.cp.parkinglot.service.ParkingService;
import org.cp.parkinglot.service.UserService;
import org.cp.parkinglot.service.VehicleService;
import org.cp.parkinglot.service.serviceImpl.ParkingServiceImpl;
import org.cp.parkinglot.service.serviceImpl.UserServiceImpl;
import org.cp.parkinglot.service.serviceImpl.VehicleServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();
        ParkingService parkingService = new ParkingServiceImpl();

        ParkingManager parkingManager = new ParkingManager(userService,vehicleService,parkingService);

        //create user
        User user1 =  parkingManager.getUserService().createUser("Abhinav","Gour","AbhinavGour7@gmail.com");

        //create vehicle
        Vehicle vehicle1 =  parkingManager.getVehicleService().createVehicle(1234L, VehicleType.CAR);

        //Register Vehicle
       boolean vehicleRegistrationStatus =  parkingManager.getUserService().vehicleRegistration(vehicle1,user1);

      if(vehicleRegistrationStatus) {
            // create parking space
          parkingService.createParkingSlots(10);
          // Park Vehicle
      Integer parkingId= parkingService.parkVehicle(user1,vehicle1);

          System.out.println("vehicle parked successfully, you vehicle id is" + parkingId);

      }
       //DashboardService

    }
}