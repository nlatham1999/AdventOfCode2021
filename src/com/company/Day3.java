package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {

    static ArrayList<String> arr1 = new ArrayList<>();
    static ArrayList<Integer> arr2 = new ArrayList<>();

    public static void Day3(){

        fileIO();

        System.out.println(arr1.size());
        System.out.println(arr2.size());
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day3Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] arr = data.split(" ");

                arr1.add(arr[0]);
                arr2.add(Integer.valueOf(arr[1]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
