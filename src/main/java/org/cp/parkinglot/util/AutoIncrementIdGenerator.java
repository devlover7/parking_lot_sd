package org.cp.parkinglot.util;

public class UserIdGenerator {

    private static int userIdCounter =0;

    public static int incrementAndGetUserIdCounter(){
        return ++userIdCounter;
    }
}
