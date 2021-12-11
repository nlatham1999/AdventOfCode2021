package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day11 {

    private static int octX = 10;
    private static int octY = 10;
    private static int[][] octupi = new int[octX][octY];
    private static boolean[][] flashed = new boolean[octX][octY];

    public static void Day11(){
        fileIO();

//        partOne();
        
        partTwo();

    }

    private static void partTwo(){

        int num = 0;
        while(!synced()){
            doOneStep();
            num++;
        }
        System.out.println(num);
    }

    private static boolean synced(){
        for(int i = 0; i < octX; i++){
            for(int j = 0; j < octY; j++){
                if(octupi[i][j] != 0)
                    return false;
            }
        }
        return true;
    }

    private static void partOne(){
//        printOctupi();

        int num = 0;
        for(int i = 0; i < 100; i++){
            num += doOneStep();
        }
        System.out.println(num);
    }


    private static int doOneStep(){
//        addOneToAll();

        setFlashedToFalse();

        goThroughOctupiAndFlash();

        return setToZeroAndScore();

    }

    private static int setToZeroAndScore(){
        int num = 0;
        for(int i = 0; i < octX; i++){
            for(int j = 0; j < octY; j++){
                if(octupi[i][j] > 9){
                    octupi[i][j] = 0;
                    num++;
                }
            }
        }
        return num;
    }



    private static void goThroughOctupiAndFlash(){
        for(int i = 0; i < octX; i++){
            for(int j = 0; j < octY; j++){
                doFlashes(i,j);
            }
        }
    }

    private static void doFlashes(int i, int j){
        octupi[i][j]++;

        if(octupi[i][j] <= 9 || flashed[i][j])
            return;

        flashed[i][j] = true;

        if(i > 0)
            doFlashes(i-1, j);
        if(i > 0 && j > 0)
            doFlashes(i-1, j-1);
        if(i > 0 && j < octY-1)
            doFlashes(i-1, j+1);
        if(j > 0)
            doFlashes(i, j-1);
        if(j < octY-1)
            doFlashes(i, j+1);
        if(i < octX-1)
            doFlashes(i+1, j);
        if(i < octX-1 && j < octY-1)
            doFlashes(i+1, j+1);
        if(i < octX-1 && j > 0)
            doFlashes(i+1, j-1);
    }

    private static void setFlashedToFalse(){
        for(int i = 0; i < octX; i++){
            for(int j = 0; j < octY; j++){
                flashed[i][j] = false;
            }
        }
    }

    private static void addOneToAll(){
        for(int i = 0; i < octX; i++){
            for(int j = 0; j < octY; j++){
                octupi[i][j]++;
            }
        }
    }

    private static void printOctupi(){
        for(int i = 0; i < octX; i++){
            for(int j = 0; j < octY; j++){
                System.out.print(octupi[i][j]);
            }
            System.out.println();
        }
    }


    private static void fileIO(){
        File inputFile = new File("src/com/company/Day11Input");
        try {
            Scanner reader = new Scanner(inputFile);
            int i = 0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                for(int j = 0; j < data.length(); j++){
                    octupi[i][j] = data.charAt(j)-'0';
                }
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
