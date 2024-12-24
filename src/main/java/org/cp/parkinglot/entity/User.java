package org.cp.parkinglot.entity;

import org.cp.parkinglot.exception.UserException;
import org.cp.parkinglot.util.AutoIncrementIdGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {
 private   int userId;
 private   String firstName;
 private   String lastName;
 private   String mailId;
 private   List<Vehicle> vehicleList;

    public User(String firstName, String lastName, String mailId) {
        if (firstName == null || firstName.isEmpty()) {
            throw new UserException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new UserException("Last name cannot be null or empty");
        }
        if (mailId == null || mailId.isEmpty()) {
            throw new UserException("Mail ID cannot be null or empty");
        }
        this.userId = AutoIncrementIdGenerator.incrementAndGetUserIdCounter();
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailId = mailId;
        this.vehicleList = new ArrayList<>();
    }

    public List<Vehicle> getVehicleList() {
        return Collections.unmodifiableList(vehicleList);
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMailId() {
        return mailId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName);
    }
    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new UserException("Cannot add a null vehicle");
        }
        this.vehicleList.add(vehicle);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mailId='" + mailId + '\'' +
                ", vehicleList=" + vehicleList.size() +
                '}';
    }
}
