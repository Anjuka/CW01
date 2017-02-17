package car.park;

import static car.park.CarParkManager.SlotArray;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Anjuka Koralage®
 */
public class FileHandler {

    public void Write() {
        try {

            File f = new File(System.getProperty("user.home") + "/Desktop");
            File file = new File(f, "WestminsterCarPark.txt");//create a text file
            file.createNewFile();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {//write data line by line

                for (Vehicle vehicle : SlotArray) {
                    bw.write(vehicle.entryDate + "-");
                    bw.write(vehicle.plateID + "-");
                    bw.write(vehicle.entryTime + "-");
                    bw.write(vehicle.type + " ");
                    bw.newLine();
                }
                bw.flush();
                bw.close();

            }

        } catch (Exception e) {
            System.err.println("Error !! ");
        }

    }

}
