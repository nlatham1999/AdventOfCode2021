package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day7 {

    private static ArrayList<Integer> crabs = new ArrayList<>();

    public static void Day7(){

        System.out.println("Test");
        fileIO();


        int min = crabs.get(0);
        int max = crabs.get(0);
        for(var crab : crabs){
            if(crab < min)
                min = crab;
            if(crab > max)
                max = crab;
        }

        int optimal = -1;
        for(int i = min; i < max; i++){
            int temp = 0;
            for(var crab: crabs){
                int n = Math.abs(crab - i);
                temp += ((n*(n+1))/2);
            }
            if(optimal == -1 || temp < optimal){
                optimal = temp;
            }
            System.out.println(i + " " + optimal);
        }
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day7Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String arr[] = data.split(",");
                for(var s : arr){
                    crabs.add(Integer.parseInt(s));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
