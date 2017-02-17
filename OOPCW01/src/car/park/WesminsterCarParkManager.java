package car.park;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anjuka Koralage®
 */
public class WesminsterCarParkManager implements CarParkManager {

    static int slots = 20;
    DateTime dateTime = new DateTime();
    static Scanner sc = new Scanner(System.in);

    //add a new vehical 
    @Override
    public void addNewVehicle(Vehicle vehicle) {
        FileHandler wD = new FileHandler();
        if (vehicle.type.equalsIgnoreCase("van")) { //validate vehicle type 
            if (slots >= 2) {
                SlotArray.add(vehicle); //add vehicle to the array
                slots -= 2; //reduce 2 lots if its a van

                System.out.println("Vehicle added to park!!");

                wD.Write();
            } else {
                System.out.println("Sorry. No Space Available!");
            }

        } else if (slots > 0) {
            SlotArray.add(vehicle);
            slots -= 1;
            System.out.println("Vehicle added to park!!");
            wD.Write();
        } else {
            System.out.println("Sorry. No Space Available!");
        }
        System.out.println("Available Number of Parking Spaces: " + slots);
        System.out.println("_______________________________________________________");
    }

    
    //delete a vehicle from parking slot
    @Override

    public void deleteVehicle(String id) {
        Iterator<Vehicle> itr = SlotArray.iterator(); //create iterator
        while (itr.hasNext()) {
            Vehicle v = itr.next();
            if (v.plateID.equals(id)) { //validate id plate no
                itr.remove();
                long exitMillis = dateTime.getCurrentTime(); //get exit time
                printVehicleDetails(v);
                System.out.println("Vehicle Removed!!");

                String time[] = dateTime.displayTime();
                System.out.println("\nExit Time: " + time[0] + " " + time[1]); //display exit time

                if (v.type.equalsIgnoreCase("Van")) {
                    slots += 2;
                } else {
                    slots += 1;
                }

                promptCharge(v, exitMillis); //display charge

            }

        }
        System.out.println("Available Parking Spaces: " + slots); //display number of free slots
        System.out.println("____________________________________________");
    }

   
        //Print Vehical list
    @Override

    public void printList() {

        if (SlotArray.isEmpty()) {
            System.out.println("No Vehicles!");
        } else {
            for (Vehicle vehicle : SlotArray) {
                printVehicleDetails(vehicle);
                System.out.println("");
            }
        }
        System.out.println("\n__________________________________");
        System.out.println("");
    }

    
    //print statics 
    @Override
    public void printStatistic() {

        System.out.println("\n---Statistics---");
        /*display parked vehicle percentages*/
        int carCount = 0;
        int vanCount = 0;
        int bikeCount = 0;
        int entryTime = 0;
        int totalCount = 0;
        double carPercentage, bikePercentage, vanPercentage;

        for (Vehicle vehicle : SlotArray) { //get vehicle count
            if (vehicle.type.equalsIgnoreCase("car")) {
                carCount++;
                totalCount++;
            } else if (vehicle.type.equalsIgnoreCase("van")) {
                vanCount++;
                totalCount++;
            } else if (vehicle.type.equalsIgnoreCase("motorbike")) {
                bikeCount++;
                totalCount++;
            }
        }

        
        // calculate percentages //////////*************************
        
        if (totalCount > 0) {
            carPercentage = ((double) carCount / (double) totalCount) * 100.0;
            vanPercentage = ((double) vanCount / (double) totalCount) * 100.0;
            bikePercentage = ((double) bikeCount / (double) totalCount) * 100.0;
            DecimalFormat pFormat = new DecimalFormat("#.00"); //define percentage format 

            /*display percentages*/
            
            System.out.println("\nPercentage of Cars: " + pFormat.format(carPercentage) + "%");
            System.out.println("Percentage of Vans: " + pFormat.format(vanPercentage) + "%");
            System.out.println("Percentage of MotorBikes: " + pFormat.format(bikePercentage) + "%");

            /*last Vehicle added to the park*/
            
            System.out.print("\nLast Vehicle: ");
            printVehicleDetails(SlotArray.get(SlotArray.size() - 1));
            System.out.println("");

            /*longest parked vehicle*/
            
            System.out.print("\nLongest Parked: ");
            printVehicleDetails(SlotArray.get(0));
            System.out.println("\n__________________________________");
            System.out.println("");
        } else {
            System.out.println("No Vehicles!!");
        }

    }

    
    // Display list according to date 
    @Override
    public void promptDayList() {

        ArrayList<String> fileData = new ArrayList<>();
        try {
            File f = new File(System.getProperty("user.home") + "/Desktop/WestminsterCarPark.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {//read until the last line
                fileData.add(line);
            }
            br.close();

//            for (String l : fileData){
//                System.out.println(l);
//            }
        } catch (IOException e) {
            System.out.println(" File not Found ");
        }

        System.out.println("---Vehicle Details---");

        System.out.print("Enter Day: ");
        String day = sc.nextLine();

        System.out.print("Enter Month (Ex: Jan, Feb..): ");
        String month = sc.nextLine();

        System.out.print("Enter Year: ");
        String year = sc.nextLine();

        String format = month + " " + day + "," + year;
        boolean var = false;
        System.out.println("");
        for (String check : fileData) {
            String dArr[] = check.split("-");

            if (dArr[0].equals(format)) {
                System.out.print(dArr[1] + " ");
                System.out.print(dArr[2] + " ");
                System.out.print(dArr[3] + " ");
                System.out.println();
                var = true;

            }

        }
        if (var == false) {
            System.out.println("No Data!!");
        }
        System.out.println("__________________________");

    }
    
    //display charge ////////////////////////////////////////////////////

    @Override
    public void promptCharge(Vehicle vehicle, long exitTime) {

        int fHrCharge = 9; //charge for first 3 hours
        int time[] = dateTime.calculateTime(vehicle.entryMillis, exitTime); //calculate duration

        double hrCharge, minCharge, chargePerMin;

        /*calculate charge for less than 3 hours*/
        if (time[1] < 3) {
            chargePerMin = 3 / 60d;
            hrCharge = (int) (time[1] * 3);
            minCharge = time[0] * chargePerMin;

            /*calculate charge for more than 3 hours*/
        } else {
            chargePerMin = 1 / 60d;
            int extraHrs = time[1] - 3;
            hrCharge = fHrCharge + extraHrs * 1;
            minCharge = time[0] * chargePerMin;
        }

        double totCharge = hrCharge + minCharge; //calculate total charge
        DecimalFormat timeFormat = new DecimalFormat("#.00"); //define charge display format
        System.out.println("Total Charge: " + timeFormat.format(totCharge));
    }
    
    // print vehicle details

    public void printVehicleDetails(Vehicle vehicle) {

        System.out.print(vehicle.plateID + "    ");
        System.out.print(vehicle.type + "    ");
        System.out.print(vehicle.entryTime + "    ");
        System.out.print(vehicle.entryDate + "    ");
    }
    
    // print delete vehicle

    private void deleteVehicle() {
        System.out.println("\n---Remove Vehicle---");
        System.out.print("Plate ID: ");
        String id = sc.nextLine();
        deleteVehicle(id);

    }

    // main menu
    public void displayMainMenu() {

        System.out.println("==========================");
        System.out.println("        MAIN MENU");
        System.out.println("==========================");
        System.out.println("  ");

        System.out.println("Please choose one of the following options:");
        System.out.println("  ");

        System.out.println("    Press A to add a vehicle to the system");
        System.out.println("    Press D to delete a vehicle from the system");
        System.out.println("    Press V to view vehicle list");
        System.out.println("    Press S to search a vehicle");
        System.out.println("    Press C to get statistics");
        System.out.println("  Press X to exit");
        System.out.println("   ");

        String userChoise = sc.nextLine();

        switch (userChoise) {
            case "A":
                promptAddVehicle();
                break;
            case "D":
                deleteVehicle();
                displayMainMenu();
                break;
            case "V":
                printList();
                displayMainMenu();
                break;
            case "S":
                promptDayList();
                displayMainMenu();
                break;
            case "C":
                printStatistic();
                displayMainMenu();
                break;
            case "X":
                System.exit(0);
                break;
            default:
                System.err.println("Invalid Input!");
                displayMainMenu();
                break;
        }

    }

    public void promptAddVehicle() {

        System.out.print("Vehicle Type : ");
        String type = sc.nextLine().toLowerCase();
        System.out.print("Plate ID : ");
        String idPlateNo = sc.nextLine();
        System.out.print("Brand : ");
        String brand = sc.nextLine();

        switch (type) {
            case "car":
                System.out.print("NoOfDoors : ");
                String nd = sc.nextLine();                                 
                int noOfDoors = Integer.parseInt(nd);
                if(noOfDoors<0){
                    System.out.println("     ");
                    System.err.println("Invalid Door Number, Please enater again!!!");
                    System.out.println("     ");
                    System.out.print("NoOfDoors : ");
                nd = sc.nextLine();     
               
                }
                
                System.out.print("Color : ");
                String color = sc.nextLine();
                System.out.println("   ");

                Car car = new Car(idPlateNo, brand, type, noOfDoors, color); //creat car object
                addNewVehicle(car); //add car object to the array

                displayMainMenu();
                
                break;
            case "van":
                System.out.print("Cargo Volume: ");
                String cv = sc.nextLine();
                int cargoVolume = Integer.parseInt(cv);
                System.out.print("\n");
                Van van = new Van(idPlateNo, brand, type, cargoVolume); //creat van object
                addNewVehicle(van); //add van object to the array

                displayMainMenu();
                break;
                
            case "motorbike":
                System.out.print("Size of Engine: ");
                String se = sc.nextLine();
                int sizeOfEngine = Integer.parseInt(se);
                System.out.print("\n");
                Motorbike bike = new Motorbike(idPlateNo, brand, type, sizeOfEngine); //creat motorbike object
                addNewVehicle(bike); //add motorbike object to the array

                displayMainMenu();
                break;
            default:
                System.err.println("Invalid Input!");
                promptAddVehicle();
                break;

        }
    }

    public static void main(String[] args) {
        WesminsterCarParkManager park = new WesminsterCarParkManager();
        park.displayMainMenu();
    }

    private void writeData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
