package com.company;

import com.sun.jdi.IntegerType;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.server.ServerNotActiveException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

class SnailPair {
    public int value = 0;
    public boolean regularValue = true;
    public SnailPair left;
    public SnailPair right;

    SnailPair(int value){
        this.value = value;
    }
}

public class Day18 {



    private static SnailPair snailPair = new SnailPair(0);
    private static int counter = 0;
    private static boolean stopTraverse = false;
    private static int level = 0;


    public static void Day18(){

        fileIO();

        partOne();
    }

    private static void partOne(){


    }

    private static void simplify(){
        stopTraverse = false;
    }

    private static SnailPair traverse(SnailPair snail){
        if(snail.regularValue){
            if(snail.value > 9){

                stopTraverse = true;
                return snail;
            }
            if(level > 4){

                stopTraverse = true;
                return snail;
            }
        }else{
            level++;
            snail.left = traverse(snail.left);
            if(!stopTraverse)
                snail.right = traverse(snail.right);
        }
        return snail;

    }


    private static void fileIO(){
        File inputFile = new File("src/com/company/Day18Input");
        try {
            Scanner reader = new Scanner(inputFile);
            ArrayList<String> list = new ArrayList<>();
            boolean notStarted = true;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                ArrayList<String> subList = new ArrayList<>();
                for(int i = 0; i < data.length(); i++){
                    if(data.charAt(i) != ','){
                        subList.add(String.valueOf(data.charAt(i)));
                    }

                }
                counter = 1;
                var snailPairTemp = constructPairs(subList);
                if(notStarted){
                    notStarted = false;
                    snailPair = snailPairTemp;
                }else{
                    var temp = snailPair;
                    snailPair = new SnailPair(0);
                    snailPair.left = temp;
                    snailPair.right = snailPairTemp;
                    snailPair.regularValue = false;
                }
            }
            printPairs(snailPair);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printPairs(SnailPair snail){
        if(snail.regularValue){
            System.out.print(snail.value);
        }else{
            System.out.print("[");
            printPairs(snail.left);
            System.out.print(",");
            printPairs(snail.right);
            System.out.print("]");
        }
    }

    private static SnailPair constructPairs(ArrayList<String> arr){
        SnailPair left;
        SnailPair right;
        SnailPair p = new SnailPair(0);
        if(arr.get(counter).equals("[")){
            counter++;
            left = constructPairs(arr);
        }else{
            left = new SnailPair(Integer.parseInt(arr.get(counter)));
            counter++;
        }
        if(arr.get(counter).equals("[")){
            counter++;
            right = constructPairs(arr);
        }else{
            right = new SnailPair(Integer.parseInt(arr.get(counter)));
            counter++;
        }
        p.left = left;
        p.right = right;
        counter++;
        p.regularValue = false;
        return p;
    }
}
