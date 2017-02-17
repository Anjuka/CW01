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
public class Motorbike extends Vehicle {

    private int engineCapacity;

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Motorbike(String plateID, String brand, String type, int engineCapacity) {
        super(plateID, brand, type);
        this.engineCapacity = engineCapacity;
    }

}
