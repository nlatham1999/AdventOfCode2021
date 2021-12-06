package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day6 {

    private static ArrayList<Integer> fish = new ArrayList<>();

    private static HashMap<Integer, Long> growthRates = new HashMap<>();

    public static void Day6(){
        System.out.println("Test");
        fileIO();

//        partOne();
        partTwo();


    }

    private static void partOne(){
        for(int i = 0; i < 80; i++){
            var newFish = fish;
            for(int j = 0; j < fish.size(); j++){
                int f = fish.get(j) - 1;
                if(f == -1){
                    f = 6;
                    newFish.add(9);
                    newFish.set(j, 6);
                }else{
                    newFish.set(j, f);
                }
            }
            fish = newFish;
            System.out.println(i + ": " + fish.size());
        }

        System.out.println(fish.size());
    }

    private static void partTwo(){
        for(var seed: fish){
            if(growthRates.containsKey(seed)){
                growthRates.put(seed, growthRates.get(seed)+1);
            }else{
                growthRates.put(seed, (long) 1);
            }
        }

        for(int i = 0; i < 256; i++){
            var growthRatesCopy = new HashMap<Integer, Long>();
            for(var key: growthRates.keySet()){
                var newAge = key - 1;
                if(newAge == -1){
                    if(growthRatesCopy.containsKey(6))
                        growthRatesCopy.put(6, growthRatesCopy.get(6) + growthRates.get(key));
                    else
                        growthRatesCopy.put(6, growthRates.get(key));
                    if(growthRatesCopy.containsKey(8))
                        growthRatesCopy.put(8, growthRatesCopy.get(8) + growthRates.get(key));
                    else
                        growthRatesCopy.put(8, growthRates.get(key));
                }else{
                    if(growthRatesCopy.containsKey(newAge))
                        growthRatesCopy.put(newAge, growthRatesCopy.get(newAge) + growthRates.get(key));
                    else
                        growthRatesCopy.put(newAge, growthRates.get(key));
                }
            }
            growthRates = growthRatesCopy;
        }

        long sum = 0;
        for(var key: growthRates.keySet()){
            sum += growthRates.get(key);
        }

        System.out.println(sum);
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day6Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                for(var x: data.split(",")){
                    fish.add(Integer.parseInt(x));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
