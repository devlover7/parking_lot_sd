package org.cp.parkinglot.service.serviceImpl;

import org.cp.parkinglot.entity.User;
import org.cp.parkinglot.entity.Vehicle;
import org.cp.parkinglot.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.cp.parkinglot.util.AutoIncrementIdGenerator.generateRandomChassisNumber;

public class UserServiceImpl implements UserService {

    List<User> userList = new ArrayList<>();
    @Override
    public User createUser(String firstName, String lastName, String mailId) {
        User user = new User(firstName,  lastName,  mailId);
         userList.add(user);
         return user;
    }

    @Override
    public boolean vehicleRegistration(Vehicle vehicle, User owner) {
        for (User user: userList) {
            if(user.equals(owner)) {
                vehicle.setOwner(user);
                vehicle.setRTORegistrationNum(generateRandomChassisNumber());
                user.addVehicle(vehicle);
            }
            return true;
        }
        return false;
    }
}
