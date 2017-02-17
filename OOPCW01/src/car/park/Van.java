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
public class Van extends Vehicle {

    private int cargoCapacity;

    public int getcargoCpacity() {
        return cargoCapacity;
    }

    public void setcargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;

    }

    public Van(String plateID, String brand, String type, int cargoCapacity) {
        super(plateID, brand, type);
        this.cargoCapacity = cargoCapacity;
    }
}
