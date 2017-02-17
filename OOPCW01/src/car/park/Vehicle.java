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
public abstract class Vehicle {

    String plateID;
    String brand;
    String type;
    long entryMillis;
    String entryDate;
    String entryTime;

    DateTime dateTime = new DateTime();

    public String getIdPlateNo() {
        return plateID;
    }

    public void setIdPlateNo(String idPlateNo) {
        this.plateID = idPlateNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate() {
        String[] time = dateTime.displayTime();
        this.entryDate = time[0];
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime() {
        String[] time = dateTime.displayTime();
        this.entryTime = time[1];
    }

    public long getEntryMillis() {
        return entryMillis;
    }

    public void setEntryMillis() {
        this.entryMillis = dateTime.getCurrentTime();
    }

    public Vehicle(String idPlateNo, String brand, String type) {
        this.plateID = idPlateNo;
        this.brand = brand;
        this.type = type;
        setEntryTime();
        setEntryDate();
        setEntryMillis();

    }

}
