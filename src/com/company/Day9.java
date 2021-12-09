package com.company;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {

    private static ArrayList<ArrayList<Integer>> floor = new ArrayList<>();
    private static HashMap<String, Boolean> map = new HashMap<>();

    public static void Day9(){

        fileIO();

        partTwo();
    }

    private static void partTwo(){
        //create map;
        for(int i = 0; i < floor.size(); i++){
            var row = floor.get(i);
            for(int j = 0; j < row.size(); j++){
                map.put(i+"-"+j, false);
            }
        }

        ArrayList<Integer> basins = new ArrayList<>();

        for(int i = 0; i < floor.size(); i++){
            var row = floor.get(i);
            for(int j = 0; j < row.size(); j++){
                if(row.get(j) != 9 && !map.get(i+"-"+j)){
                    int count = helper(i,j,0);
                    basins.add(count);
                }
            }
        }

        Collections.sort(basins, Collections.reverseOrder());
        System.out.println(basins);
        System.out.println(basins.get(0) * basins.get(1) * basins.get(2));

    }

    private static int helper(int i, int j, int count){
        map.put(i+"-"+j, true);
        count += 1;

        if(i>0 && floor.get(i-1).get(j) != 9 && !map.get((i-1)+"-"+j)){
            count += helper(i-1, j, 0);
        }
        if(i<floor.size()-1 && floor.get(i+1).get(j) != 9 && !map.get((i+1)+"-"+j)){
            count += helper(i+1, j, 0);
        }
        if(j>0 && floor.get(i).get(j-1) != 9 && !map.get((i)+"-"+(j-1))){
            count += helper(i, j-1, 0);
        }
        if(j<floor.get(0).size()-1 && floor.get(i).get(j+1) != 9 && !map.get((i)+"-"+(j+1))){
            count += helper(i, j+1, 0);
        }

        return count;
    }

    private static void partOne(){
        int sum = 0;
        for(int i = 0; i < floor.size(); i++){
            var row = floor.get(i);
            for(int j = 0; j < row.size(); j++){
                if(!(j > 0 && row.get(j-1) <= row.get(j)) &&
                        !(j < row.size()-1 && row.get(j+1) <= row.get(j)) &&
                        !(i > 0 && floor.get(i-1).get(j) <= row.get(j)) &&
                        !(i < floor.size()-1 && floor.get(i+1).get(j) <= row.get(j))
                ){
                    System.out.println(row.get(j));
                    sum += row.get(j) + 1;
                }
            }
        }

        System.out.println(sum);
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day9Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                ArrayList<Integer> row = new ArrayList<>();
                for(int i = 0; i < data.length(); i++){
                    row.add(data.charAt(i) - '0');
                }
                floor.add(row);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
