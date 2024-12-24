import org.cp.parkinglot.entity.User;
import org.cp.parkinglot.entity.Vehicle;
import org.cp.parkinglot.entity.enums.VehicleType;
import org.cp.parkinglot.exception.VehicleException;
import org.cp.parkinglot.manager.ParkingManager;
import org.cp.parkinglot.service.ParkingService;
import org.cp.parkinglot.service.UserService;
import org.cp.parkinglot.service.VehicleService;
import org.cp.parkinglot.service.serviceImpl.ParkingServiceImpl;
import org.cp.parkinglot.service.serviceImpl.UserServiceImpl;
import org.cp.parkinglot.service.serviceImpl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ParkingLotTest {

    private ParkingManager parkingManager;

    @BeforeEach
    public void setUp() {
        UserService userService = new UserServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();
        ParkingService parkingService = new ParkingServiceImpl();
        parkingManager = new ParkingManager(userService, vehicleService, parkingService);
    }

    @Test
    public void testCreateUser() {
        User user = parkingManager.getUserService().createUser("John", "Doe", "john.doe@gmail.com");
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void testCreateVehicle() {
        Vehicle vehicle = parkingManager.getVehicleService().createVehicle(12345L, VehicleType.CAR);
        assertNotNull(vehicle);
        assertEquals(VehicleType.CAR, vehicle.getVehicleType());
    }

    @Test
    public void testRegisterVehicle() {
        User user = parkingManager.getUserService().createUser("John", "Doe", "john.doe@gmail.com");
        Vehicle vehicle = parkingManager.getVehicleService().createVehicle(12345L, VehicleType.CAR);
        boolean status = parkingManager.getUserService().vehicleRegistration(vehicle, user);
        assertTrue(status);
    }

    @Test
    public void testParkVehicle() {
        User user = parkingManager.getUserService().createUser("John", "Doe", "john.doe@gmail.com");
        Vehicle vehicle = parkingManager.getVehicleService().createVehicle(12345L, VehicleType.CAR);
        parkingManager.getUserService().vehicleRegistration(vehicle, user);
        parkingManager.getParkingService().createParkingSlots(10);
        
        Integer parkingId = parkingManager.getParkingService().parkVehicle(user, vehicle);
        assertNotNull(parkingId);
    }

    @Test
    public void testConcurrentParking() {
        // Simulate concurrent parking attempts
        Thread t1 = new Thread(() -> {
            // Park first vehicle
            User user = parkingManager.getUserService().createUser("User1", "One", "user1@gmail.com");
            Vehicle vehicle = parkingManager.getVehicleService().createVehicle(123L, VehicleType.CAR);
            parkingManager.getUserService().vehicleRegistration(vehicle, user);
            parkingManager.getParkingService().createParkingSlots(10);
            assertNotNull(parkingManager.getParkingService().parkVehicle(user, vehicle));
        });

        Thread t2 = new Thread(() -> {
            // Attempt to park another vehicle
            User user = parkingManager.getUserService().createUser("User2", "Two", "user2@gmail.com");
            Vehicle vehicle = parkingManager.getVehicleService().createVehicle(124L, VehicleType.CAR);
            parkingManager.getUserService().vehicleRegistration(vehicle, user);
            parkingManager.getParkingService().createParkingSlots(10);
            assertNotNull(parkingManager.getParkingService().parkVehicle(user, vehicle));
        });

        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCapacityOverflow() {
        parkingManager.getParkingService().createParkingSlots(1);
        User user1 = parkingManager.getUserService().createUser("User1", "One", "user1@gmail.com");
        Vehicle vehicle1 = parkingManager.getVehicleService().createVehicle(123L, VehicleType.CAR);
        parkingManager.getUserService().vehicleRegistration(vehicle1, user1);
        Integer parkingId1 = parkingManager.getParkingService().parkVehicle(user1, vehicle1);
        assertNotNull(parkingId1);
        
        User user2 = parkingManager.getUserService().createUser("User2", "Two", "user2@gmail.com");
        Vehicle vehicle2 = parkingManager.getVehicleService().createVehicle(124L, VehicleType.CAR);
        parkingManager.getUserService().vehicleRegistration(vehicle2, user2);
        
        assertThrows(VehicleException.class, () -> {
            parkingManager.getParkingService().parkVehicle(user2, vehicle2);
        });
    }
}
