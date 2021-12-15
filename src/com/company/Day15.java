package com.company;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day15 {

    private static int size = 10;
    private static int[][] ceiling = new int[size][size];

    private static int smallest = Integer.MAX_VALUE;

    private static HashMap<String, Integer> bigHistory = new HashMap<>();

    private static ArrayList<Pair<Integer, Integer>> unvisited = new ArrayList<>();
    private static ArrayList<Pair<Integer, Integer>> visited = new ArrayList<>();
    private static int[][] distances = new int[size][size];

    private static PriorityQueue<Pair<String,Integer>> pq=
            new PriorityQueue<Pair<String,Integer>>(Comparator.comparing(Pair::getKey));

    public static void Day15(){

        fileIO();

//        partOne();

        partTwo();
    }

    private static void partTwo(){
        int[][] ceiling2 = new int[size*5][size*5];
        for(int x = 0; x < 5; x++){
            for(int y = 0; y < 5; y++){
                for(int i = 0; i < size; i++){
                    for(int j = 0; j < size; j++){

                        int val = ceiling[i][j] + x + y;
                        if(val > 9){
                            val = 1;
                        }

                        ceiling2[(x+1)*i][(y+1)*j] = val;
                    }
                }
            }
        }
        ceiling = ceiling2;
        size = size*5;
        distances = new int[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){

                if(i == 0 && j == 0){
                    distances[i][j] = 0;
                }else{
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }
        }



        ArrayList<Pair<Integer, Integer>> q = new ArrayList<>();
        q.add(new Pair<>(0, 0));
        while(q.size() > 0){
            int i = q.get(0).getKey();
            int j = q.get(0).getValue();
            if(i > 0){
                if(ceiling[i-1][j] + distances[i][j] < distances[i-1][j]){
                    distances[i-1][j] = ceiling[i-1][j] + distances[i][j];
                    q.add(new Pair<>(i-1, j));
                }
            }

            if(i < size-1){
                if(ceiling[i+1][j] + distances[i][j] < distances[i+1][j]){
                    distances[i+1][j] = ceiling[i+1][j] + distances[i][j];
                    q.add(new Pair<>(i+1, j));
                }
            }

            if(j > 0){
                if(ceiling[i][j-1] + distances[i][j] < distances[i][j-1]){
                    distances[i][j-1] = ceiling[i][j-1] + distances[i][j];
                    q.add(new Pair<>(i, j-1));
                }
            }

            if(j < size-1){
                if(ceiling[i][j+1] + distances[i][j] < distances[i][j+1]){
                    distances[i][j+1] = ceiling[i][j+1] + distances[i][j];
                    q.add(new Pair<>(i, j+1));
                }
            }

            q.remove(0);
        }

//        traverse(0,0);

        System.out.println(distances[size-1][size-1]);
    }

    private static void partOne(){

       for(int i = 0; i < size; i++){
           for(int j = 0; j < size; j++){

               if(i == 0 && j == 0){
                   distances[i][j] = 0;
               }else{
                   distances[i][j] = Integer.MAX_VALUE;
               }

               unvisited.add(new Pair<>(i, j));

           }
       }

       System.out.println(unvisited);
       System.out.println(distances);

       traverse(0,0);

       System.out.println(distances[size-1][size-1]);
    }

    private static void traverse(int i, int j){

        if(i > 0){
            if(ceiling[i-1][j] + distances[i][j] < distances[i-1][j]){
                distances[i-1][j] = ceiling[i-1][j] + distances[i][j];
                traverse(i-1,j);
            }
        }

        if(i < size-1){
            if(ceiling[i+1][j] + distances[i][j] < distances[i+1][j]){
                distances[i+1][j] = ceiling[i+1][j] + distances[i][j];
                traverse(i+1,j);
            }
        }

        if(j > 0){
            if(ceiling[i][j-1] + distances[i][j] < distances[i][j-1]){
                distances[i][j-1] = ceiling[i][j-1] + distances[i][j];
                traverse(i,j-1);
            }
        }

        if(j < size-1){
            if(ceiling[i][j+1] + distances[i][j] < distances[i][j+1]){
                distances[i][j+1] = ceiling[i][j+1] + distances[i][j];
                traverse(i,j+1);
            }
        }
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day15Input");
        try {
            Scanner reader = new Scanner(inputFile);
            int j = 0;

            while (reader.hasNextLine()) {

                String data = reader.nextLine();
                for(int i = 0; i < data.length();i++){
                    ceiling[j][i] = Integer.parseInt(Character.toString(data.charAt(i)));
                }
                j++;
            }
//            System.out.println(ceiling.size() + " " + ceiling.get(0).size());
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
