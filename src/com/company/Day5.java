package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day5 {

    private static ArrayList<Integer> x1 = new ArrayList<>();
    private static ArrayList<Integer> x2 = new ArrayList<>();
    private static ArrayList<Integer> y1 = new ArrayList<>();
    private static ArrayList<Integer> y2 = new ArrayList<>();

    private static HashMap<String, Integer> points = new HashMap<>();

    public static void Day5(){

        fileIO();
        partOne();
    }

    private static void partOne(){
        System.out.println(x1);
        System.out.println(y1);
        System.out.println(x2);
        System.out.println(y2);

        int max = 0;
        for(int i = 0; i < x1.size(); i++){
            if(x1.get(i) > max){
                max = x1.get(i);
            }
            if(x2.get(i) > max){
                max = x2.get(i);
            }
            if(y1.get(i) > max){
                max = y1.get(i);
            }
            if(y2.get(i) > max){
                max = y2.get(i);
            }
        }

        int[][] field = new int[max+1][max+1];
        for(int i = 0; i < max+1; i++){
            for(int j = 0; j < max+1; j++){
                field[i][j] = 0;
            }
        }

        for(int i = 0; i < x1.size(); i++){
            if(x1.get(i) == x2.get(i)){
                int start = y1.get(i) < y2.get(i) ? y1.get(i) : y2.get(i);
                int end = y1.get(i) > y2.get(i) ? y1.get(i) : y2.get(i);
                for(int j = start; j <= end; j++){
                    field[x1.get(i)][j]++;
                }
            }
            if(y1.get(i) == y2.get(i)){
                int start = x1.get(i) < x2.get(i) ? x1.get(i) : x2.get(i);
                int end = x1.get(i) > x2.get(i) ? x1.get(i) : x2.get(i);
                for(int j = start; j <= end; j++){
                    field[j][y1.get(i)]++;
                }
            }
        }


        int count = 0;
        for(int i = 0; i < max+1; i++){
            for(int j = 0; j < max+1; j++){
                if(field[i][j] > 1){
                    count += 1;
                }
            }
        }
        System.out.println(count);
        System.out.println(x1.size() + " " + x2.size() + " " + y1.size() + " " + y2.size());
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day5Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                var arr = data.split(" ");
                x1.add(Integer.valueOf(arr[0].split(",")[0]));
                y1.add(Integer.valueOf(arr[0].split(",")[1]));
                x2.add(Integer.valueOf(arr[2].split(",")[0]));
                y2.add(Integer.valueOf(arr[2].split(",")[1]));
//                arr0.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
