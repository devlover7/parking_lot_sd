package org.cp.parkinglot.service.serviceImpl;

import org.cp.parkinglot.entity.ParkingFloor;
import org.cp.parkinglot.entity.ParkingSlots;
import org.cp.parkinglot.entity.User;
import org.cp.parkinglot.entity.Vehicle;
import org.cp.parkinglot.entity.enums.VehicleType;
import org.cp.parkinglot.exception.VehicleException;
import org.cp.parkinglot.service.ParkingService;

import java.util.ArrayList;
import java.util.List;

public class ParkingServiceImpl implements ParkingService {

    List<ParkingFloor> parkingFloorList = new ArrayList<>();

    public void createParkingSlots(int capacity){
        List<ParkingSlots> parkingSlotsList = new ArrayList<>();
        int truckSlots = (int) (capacity * 0.10);  // 10% for trucks
        int carSlots = (int) (capacity * 0.40);    // 40% for cars
        int bikeSlots = (int) (capacity * 0.40);   // 40% for bikes

        for (int i = 0; i < truckSlots; i++) {
            parkingSlotsList.add(new ParkingSlots(VehicleType.TRUCK));
        }

        for (int i = 0; i < carSlots; i++) {
            parkingSlotsList.add(new ParkingSlots(VehicleType.CAR));
        }

        for (int i = 0; i < bikeSlots; i++) {
            parkingSlotsList.add(new ParkingSlots(VehicleType.BIKE));
        }
        createParkingSpace(capacity,parkingSlotsList);
    }


    public void createParkingSpace(int capacity,List<ParkingSlots> parkingSlotsList){
        for (int i = 0; i < 10; i++) {
            parkingFloorList.add(new ParkingFloor(i,capacity,parkingSlotsList));
        }
    }

    @Override
    public Integer parkVehicle(User user, Vehicle vehicle) {
       int parkingId =-1;
        for(Vehicle userVehicle: user.getVehicleList()) {
           if(userVehicle.getOwner()!=user) throw new VehicleException("Vehicle not belongs to user");
       }
        for(ParkingFloor parkingFloor:parkingFloorList){
            List<ParkingSlots> parkingSlots = parkingFloor.getAvailableSlots();
            if(!parkingSlots.isEmpty())
            {
                for(ParkingSlots parking: parkingSlots){
                    if(parking.getVehicleType()==vehicle.getVehicleType()) {
                        parking.setVehicle(vehicle);
                        parkingId = parking.getParkingId();
                        break;
                    }
                }
                    if(parkingId==-1)
                        throw  new VehicleException("No slot avaialable for "+ vehicle.getVehicleType());

                break;
            }
       }
        return parkingId;
    }
}
