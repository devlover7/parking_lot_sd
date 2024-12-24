package org.cp.parkinglot.service;

import org.cp.parkinglot.entity.User;
import org.cp.parkinglot.entity.Vehicle;

public interface UserService {

    User createUser(String firstName, String lastName, String mailId);

    boolean vehicleRegistration(Vehicle vehicle, User user);


}
