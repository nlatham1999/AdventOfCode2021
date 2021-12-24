package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day23 {

    public static void Day23(){

        fileIO();
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day23Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
