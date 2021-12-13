package com.company;

import javafx.util.Pair;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day13 {

    private static HashMap<String, Pair<Integer, Integer>> holes = new HashMap<>();
    private static ArrayList<Pair<String, Integer>> folds = new ArrayList<>();

    public static void Day13 () {

        fileIO();

        partOne();

        partTwo();


    }

    private static void partTwo(){
        for(var x: folds){
            doOneFold(x.getKey(),x.getValue());
        }

        System.out.println(holes);

        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 40; j++){
                if(holes.containsKey(j+"-"+i)){
                    System.out.print("#");
                }else{
                    System.out.print('.');
                }
            }
            System.out.println();
        }

    }

    private static void partOne(){
        System.out.println(holes.size());
        System.out.println(folds);

        doOneFold(folds.get(0).getKey(),folds.get(0).getValue());

        System.out.println(holes.size());
    }

    private static void doOneFold(String direction, int amount){
        HashMap<String, Pair<Integer, Integer>> newHoles = new HashMap<>();

        for(var key: holes.keySet()){
            Pair<Integer, Integer> coord = holes.get(key);
            if(direction.equals("x")){
                if(coord.getKey() > amount){
                    int newX = amount-coord.getKey()+amount;
                    coord = new Pair<>(newX, coord.getValue());
                }
            }else{
                if(coord.getValue() > amount){
                    int newY = amount-coord.getValue()+amount;
                    coord = new Pair<>(coord.getKey(), newY);
                }
            }
            newHoles.put(coord.getKey()+"-"+coord.getValue(), coord);
        }

        holes = newHoles;
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day13Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                if(data.contains("fold")){
                    String[] arr = data.split("=");
                    folds.add(new Pair<>(arr[0].split(" ")[2], Integer.parseInt(arr[1])));
                }else if(!data.equals("")){
                    String[] arr = data.split(",");
                    holes.put(arr[0]+"-"+arr[1], new Pair<Integer, Integer>(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
