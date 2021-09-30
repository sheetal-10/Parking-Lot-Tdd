package com.parkingLot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @BeforeEach
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isUnParked();
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking Lot is full", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenParkedAnotherVehicle_ShouldReturnFalse() {
        Object anotherVehicle = new Object();
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(anotherVehicle);
            Assertions.assertFalse(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parkingLotWhenFull_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        boolean isFull = parkingLotSystem.isParkingLotFull();
        Assertions.assertTrue(isFull);
    }

    @Test
    public void parkingLotWhenNotFull_ShouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isEmpty = parkingLotSystem.isParkingLotFull();
        Assertions.assertFalse(isEmpty);
    }

    @Test
    public void parkingLotWhenFull_ShouldRedirectToAirportStaff() throws ParkingLotException {
        try {
            parkingLotSystem.park(vehicle);
            if (parkingLotSystem.isParkingLotFull()) {
                throw new ParkingLotIndicator("Parking Lot is Full");
            }
        } catch (ParkingLotIndicator e){
            Assertions.assertEquals("Parking Lot is Full",e.getMessage());
        }
    }
}
