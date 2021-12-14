package com.company;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day14 {

    private static String polymer = "";
    private static HashMap<String, String> instructions = new HashMap<>();

    private static HashMap<String, Long> mappings = new HashMap<>();

    public static void Day14(){

        fileIO();

//        partOne();

        partTwo();
    }

    private static void partTwo(){
        System.out.println(polymer);

        for(int i = 0; i < polymer.length()-1; i++){
            String key = Character.toString(polymer.charAt(i)) + Character.toString(polymer.charAt(i+1));
            if(mappings.containsKey(key)){
                mappings.put(key, mappings.get(key)+1);
            }else{
                mappings.put(key, (long) 1);
            }
        }

        for(int i = 0; i < 40; i++){
            HashMap<String, Long> mappingsCopy = (HashMap<String, Long>) mappings.clone();
            for(var key : mappings.keySet()){
                if(instructions.containsKey(key)){
                    String newKeyOne = key.charAt(0)+instructions.get(key);
                    String newKeyTwo = instructions.get(key) + key.charAt(1);
                    mappingsCopy.put(key, mappingsCopy.get(key)-mappings.get(key));
                    if(mappingsCopy.containsKey(newKeyOne)){
                        mappingsCopy.put(newKeyOne, mappingsCopy.get(newKeyOne)+mappings.get(key));
                    }else{
                        mappingsCopy.put(newKeyOne, mappings.get(key));
                    }
                    if(mappingsCopy.containsKey(newKeyTwo)){
                        mappingsCopy.put(newKeyTwo, mappingsCopy.get(newKeyTwo)+mappings.get(key));
                    }else{
                        mappingsCopy.put(newKeyTwo, mappings.get(key));
                    }
                }
            }
            mappings = mappingsCopy;
        }

        System.out.println(mappings);
        HashMap<Character, Long> counts = new HashMap<>();
        for(var key: mappings.keySet()){
            if(counts.containsKey(key.charAt(1))){
                counts.put(key.charAt(1), counts.get(key.charAt(1)) + mappings.get(key));
            }else{
                counts.put(key.charAt(1), mappings.get(key));
            }
        }
        System.out.println(counts);

        long largest = 0;
        long smallest = 0;
        int i = 0;
        for(var key : counts.keySet()){
            if(counts.get(key) != 0){
                if(i == 0){
                    smallest = counts.get(key);
                    i++;
                }
                if(counts.get(key) < smallest){
                    smallest = counts.get(key);
                }
                if(counts.get(key) > largest){
                    largest = counts.get(key);
                }
            }
        }
        System.out.println(largest + " " + smallest + " " + (largest-smallest));
    }

    private static void partOne(){
        System.out.println(polymer);

        System.out.println(instructions.size());

        for(int j = 0; j < 10; j++){
            System.out.println(j);
            String newPolymer = Character.toString(polymer.charAt(0));
            for(int i = 0; i < polymer.length()-1; i++){
                String key = Character.toString(polymer.charAt(i)) + Character.toString(polymer.charAt(i+1));
                newPolymer += instructions.get(key) + Character.toString(polymer.charAt(i+1));
            }
            polymer = newPolymer;
        }

        System.out.println(polymer);

        HashMap<Character, Integer> counts = new HashMap<>();
        for(int i = 0; i < polymer.length(); i++){
            if(counts.containsKey(polymer.charAt(i))){
                counts.put(polymer.charAt(i), counts.get(polymer.charAt(i)) + 1);
            }else{
                counts.put(polymer.charAt(i), 1);
            }
        }

        int largest = 0;
        int smallest = Integer.MAX_VALUE;
        for(var key : counts.keySet()){
            if(counts.get(key) < smallest){
                smallest = counts.get(key);
            }
            if(counts.get(key) > largest){
                largest = counts.get(key);
            }
        }
        System.out.println(largest-smallest);
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day14Input");
        try {
            Scanner reader = new Scanner(inputFile);
            int i = 0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if(i == 0){
                    polymer = data;
                }
                if(i > 1){
                    String[] arr = data.split(" -> ");
                    instructions.put(arr[0], arr[1]);
                }

                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
