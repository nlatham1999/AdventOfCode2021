package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {

    static ArrayList<String> arr0 = new ArrayList<>();
    static ArrayList<Integer> arr1 = new ArrayList<>();
    static ArrayList<String> arrGamma = new ArrayList<>();
    static ArrayList<String> arrEpsilon = new ArrayList<>();

    public static void Day3(){
        fileIO2();
        arrGamma = arr0;
        arrEpsilon = arr0;

        arrGamma = filterDown(arrGamma, 1, 0);
        arrEpsilon = filterDown(arrEpsilon, 0, 1);

        System.out.println(arrGamma);
        System.out.println(arrEpsilon);
        System.out.println(Integer.parseInt(arrGamma.get(0), 2)*Integer.parseInt(arrEpsilon.get(0), 2));
    }

    private static ArrayList<String> filterDown(ArrayList<String> arr, int first, int second){
        for(int i = 0; i < 12; i++){
            arr1 = new ArrayList<>();
            readArray(i, arr);
            int ones = 0;
            int zeros = 0;
            for(int j = 0; j < arr1.size(); j++){
                if(arr1.get(j) == 0){
                    zeros += 1;
                }else{
                    ones += 1;
                }
            }
            if(ones >= zeros){
                arr = filterArr(first, i, arr);
            }else{
                arr = filterArr(second, i, arr);
            }
            if(arr.size()==1)
                return arr;
        }
        return arr;
    }

    private static ArrayList<String> filterArr(int i, int pos, ArrayList<String> arr){
        if(arr.size() == 1)
            return arr;

        ArrayList<String> arrC = new ArrayList<>();

        for(int j = 0; j < arr.size(); j++){
            if(Integer.valueOf(arr.get(j).charAt(pos)-48) == i){
                arrC.add(arr.get(j));
            }
        }

        arr = arrC;
        return arr;
    }

    private static void fileIO2(){
        File inputFile = new File("src/com/company/Day3Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                arr0.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readArray(int i, ArrayList<String> arr){
        arr1 = new ArrayList<>();
        for(int j = 0; j < arr.size(); j++){
            arr1.add(Integer.valueOf(arr.get(j).charAt(i))-48);
        }
    }

}
