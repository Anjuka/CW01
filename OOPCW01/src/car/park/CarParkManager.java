package car.park;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anjuka Koralage®
 */
public interface CarParkManager {

    static final int size = 20;
    static ArrayList<Vehicle> SlotArray = new ArrayList<>(20);

    void addNewVehicle(Vehicle vehicle);

    void deleteVehicle(String p_id);

    void printList();

    void printStatistic();

    void promptDayList();

    void promptCharge(Vehicle vehicle, long exitTime);
}
