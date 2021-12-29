package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day25 {

    private static int MAX_X = 137;
    private static int MAX_Y = 139;
    private static char[][] cucumbers = new char[MAX_X][MAX_Y];
    private static char[][] template = new char[MAX_X][MAX_Y];

    public static void Day25(){

        fileIO();

        setTemplate();

        partOne();
    }

    private static void setTemplate(){
        for(int i = 0; i < MAX_X; i++){
            for(int j = 0; j < MAX_Y; j++){
                template[i][j] = '.';
            }
        }
    }

    private static void printCucumbers(char[][] arr){
        for(int i = 0; i < MAX_X; i++){
            for(int j = 0; j < MAX_Y; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    private static void partOne(){


        boolean cantMove = false;
        int steps = 0;
        while(!cantMove){
            cantMove = true;
            setTemplate();
//            printCucumbers(cucumbers);
            for(int i = 0; i < MAX_X; i++){
                for(int j = 0; j < MAX_Y; j++){
                    if(cucumbers[i][j] == '>'){
                        if(cucumbers[i][(j+1)%MAX_Y] == '.'){
                            template[i][(j+1)%MAX_Y] = '>';
                            cantMove = false;
                        }else{
                            template[i][j] = '>';
                        }
                    }else if(cucumbers[i][j] == 'v'){
                        if(!(cucumbers[(i+1)%MAX_X][j] == '.' && cucumbers[(i+1)%MAX_X][Math.floorMod(j-1,MAX_Y)] == '>')
                                && (cucumbers[(i+1)%MAX_X][j] != 'v')
                                && !(cucumbers[(i+1)%MAX_X][j] == '>' && cucumbers[(i+1)%MAX_X][(j+1)%MAX_Y] != '.')
                        ){
                            template[(i+1)%MAX_X][j] = 'v';
                            cantMove = false;
                        }else{
                            template[i][j] = 'v';
                        }
                    }
                }
            }
            for(int i = 0; i < MAX_X; i++){
                for(int j = 0; j < MAX_Y; j++){
                    cucumbers[i][j] = template[i][j];
                }
            }
            System.out.println(steps);
            steps++;

        }
        System.out.println(steps);
        printCucumbers(cucumbers);
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day25Input");
        try {
            Scanner reader = new Scanner(inputFile);
            int i = 0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                for(int j = 0; j < data.length(); j++){
                    cucumbers[i][j] = data.charAt(j);
                }
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
