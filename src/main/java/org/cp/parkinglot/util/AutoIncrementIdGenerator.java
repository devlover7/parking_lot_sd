package org.cp.parkinglot.util;

import java.util.concurrent.atomic.AtomicInteger;

public class AutoIncrementIdGenerator {

    private static AtomicInteger userIdCounter =new AtomicInteger(0);
    private static AtomicInteger vehicleIdCounter =new AtomicInteger(0) ;
    private static AtomicInteger parkingFloodIdCounter =new AtomicInteger(0);
    private static AtomicInteger parkingSlotIdCounter=new AtomicInteger(0);

    public static int incrementAndGetUserIdCounter(){
        return userIdCounter.incrementAndGet();
    }

    public static int incrementAndGetVehicleIdCounter(){
        return vehicleIdCounter.incrementAndGet();
    }

    public static int incrementAndGetParkingFloorIdCounter(){
        return parkingFloodIdCounter.incrementAndGet();
    }

    public static int incrementAndGetParkingSlotIdCounter(){
        return parkingSlotIdCounter.incrementAndGet();
    }

    public static String generateRandomChassisNumber() {
        return String.valueOf (Math.random() * 1_000_000_000L);  // Random 9-digit chassis number
    }
}
