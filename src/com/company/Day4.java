package com.company;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;


public class Day4 {

    private static ArrayList<Integer> drawings = new ArrayList<>();
    private static ArrayList<Pair<Integer, ArrayList<Integer>>> rowsAndColumns = new ArrayList<>();
    private static ArrayList<HashMap<Integer, Triplet>> elementsMap = new ArrayList<>(); //map of bingo cell -> row, column, istaken
    private static ArrayList<Integer> boardsInPlay = new ArrayList<>();

    public static void Day4(){
        fileIO();
        partTwo();
    }

    private static void partTwo(){

        for(int i = 0; i < 100; i++){
            boardsInPlay.add(i);
        }

        for(var drawing : drawings){

            //look through all bingo cards
            for(int i = 0; i < elementsMap.size(); i++){
                var bingoCard = elementsMap.get(i);
                //remove value from column and row
                if(bingoCard.containsKey(drawing)){
                    var pos = bingoCard.get(drawing);

                    var colRow = rowsAndColumns.get(i*10+5+pos.second);
                    var rowRow = rowsAndColumns.get(i*10+pos.first);
                    var colList = colRow.getValue();
                    var rowList = rowRow.getValue();
                    colList.remove(colList.indexOf(drawing));
                    rowList.remove(rowList.indexOf(drawing));

                    //set all updated values
                    pos.third = 1;
                    bingoCard.replace(drawing, pos);
                    elementsMap.set(i, bingoCard);

                    if(colList.size() == 0 || rowList.size() == 0){

                        int sum = 0;
                        for(Integer x: bingoCard.keySet()){
                            var trip = bingoCard.get(x);
                            if(trip.third == 0){
                                sum += x;
                            }
                        }

                        System.out.println(i + " : " + sum*drawing);
                        elementsMap.set(i, new HashMap<>());
                        boardsInPlay.remove(boardsInPlay.indexOf(i));
                        if(boardsInPlay.size() == 0)
                            return;
                    }

                    rowsAndColumns.set(i*10+5+pos.second, new Pair(colRow.getKey(), colList));
                    rowsAndColumns.set(i*10+pos.first, new Pair(rowRow.getKey(), rowList));

                }
            }
        }
    }

    private static void partOne(){
        for(var drawing : drawings){

            //look through all bingo cards
            for(int i = 0; i < elementsMap.size(); i++){
                var bingoCard = elementsMap.get(i);
                //remove value from column and row
                if(bingoCard.containsKey(drawing)){
                    var pos = bingoCard.get(drawing);


                    var colRow = rowsAndColumns.get(i*10+5+pos.second);
                    var rowRow = rowsAndColumns.get(i*10+pos.first);
                    var colList = colRow.getValue();
                    var rowList = rowRow.getValue();
                    colList.remove(colList.indexOf(drawing));
                    rowList.remove(rowList.indexOf(drawing));

                    //set all updated values
                    pos.third = 1;
                    bingoCard.replace(drawing, pos);
                    elementsMap.set(i, bingoCard);

                    if(colList.size() == 0 || rowList.size() == 0){

                        int sum = 0;
                        for(Integer x: bingoCard.keySet()){
                            var trip = bingoCard.get(x);
                            if(trip.third == 0){
                                System.out.println(x);
                                sum += x;
                            }
                        }
                        return;
                    }

                    rowsAndColumns.set(i*10+5+pos.second, new Pair(colRow.getKey(), colList));
                    rowsAndColumns.set(i*10+pos.first, new Pair(rowRow.getKey(), rowList));

                }
            }
        }
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day4Input");
        try {
            Scanner reader = new Scanner(inputFile);
            String data = reader.nextLine();
            for(String el : data.split(",")){
                drawings.add(Integer.parseInt(el));
            }
            reader.nextLine();

            int mapIndex = 0;

            ArrayList<ArrayList<Integer>> columns = new ArrayList<>();
            for(int i = 0; i < 5; i++){
                columns.add(new ArrayList<>());
            }

            HashMap<Integer, Triplet> map = new HashMap<>();

            int rowCount = 0;

            while (reader.hasNextLine()) {
                data = reader.nextLine();
                if(data.length() == 0){
                    for(var column : columns){
                        rowsAndColumns.add(new Pair<>(mapIndex, column));
                    }

                    columns = new ArrayList<>();
                    for(int i = 0; i < 5; i++){
                        columns.add(new ArrayList<>());
                    }

                    elementsMap.add(map);
                    map = new HashMap<>();

                    mapIndex++;
                    rowCount = 0;
                }else{
                    String[] arr = data.split(" ");
                    ArrayList<Integer> row = new ArrayList<>();
                    int count = 0;
                    for(int i = 0; i < arr.length; i++){
                        if(arr[i].length() > 0){
                            int val = Integer.parseInt(arr[i]);

                            var col = columns.get(count);
                            col.add(val);
                            columns.set(count, col);

                            row.add(val);

                            map.put(val, new Triplet(rowCount, count, 0));
                            count++;

                        }
                    }
                    rowsAndColumns.add(new Pair<>(mapIndex, row));
                    rowCount ++;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
