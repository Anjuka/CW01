package car.park;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anjuka Koralage®
 */
public class Car extends Vehicle {

    private int noOfDoor;
    private String color;

    public int getNoOfDoor() {
        return noOfDoor;
    }

    public void setNoOfDoors(int noOfDoor) {
        this.noOfDoor = noOfDoor;
    }

    public String getcolor() {
        return color;
    }

    public void setcolor(String color) {
        this.color = color;
    }

    public Car(String plateID, String brand, String type, int noOfDoor, String color) {
        super(plateID, brand, type);
        this.noOfDoor = noOfDoor;
        this.color = color;
    }
}
