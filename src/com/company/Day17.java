package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Day17 {

    private static int minX = 211;
    private static int maxX = 232;
    private static int minY = -124;
    private static int maxY = -69;
    private static int highest = 0;
    private static int countOfValid = 0;

    public static void Day17(){

//        fileIO();
        partOne();
//        willHitTarget(7,2);
    }

    private static void partOne(){

        for(int x = 0; x <= maxX; x++){
            for(int y = minY; y < (-1*minY); y++){
                willHitTarget(x,y);
            }
        }

    }

    private static boolean willHitTarget(int x, int y){

        int initX = x;
        int initY = y;
        int high = 0;
        int countY = y;
        int countX = x;
        while (true){
//            System.out.println(x + " " + y);
            if(y > high){
                high = y;
            }
            if(x > maxX || y < minY){
//                System.out.println("not it");
                return false;
            }
            if(x >= minX && x <= maxX && y >= minY && y <= maxY){
                if(high > highest){
                    highest = high;
                    System.out.println(initX + " " + initY + " " + highest);
                }
                countOfValid++;
                System.out.println(countOfValid);
                return true;
            }
            if(countX > 0){
                countX--;
            }
            countY--;
            x += countX;
            y += countY;
        }
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day17Input");
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
