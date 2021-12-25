package com.company;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Day23 {

    private static ArrayList<Pair<Character, Integer>> amphipods = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static int lowScore = Integer.MAX_VALUE;

    public static void Day23(){

        setup();

        partOne();
    }

    private static void partOne(){

        traverse(amphipods, 0, 0);

        System.out.print(lowScore);
    }

    private static void traverse(ArrayList<Pair<Character, Integer>> amp, int score, int level){
        HashMap<Integer, Character> location = new HashMap<>();
        for(var a : amp){
            location.put(a.getValue(), a.getKey());
        }


//        printHouse(location);
//        System.out.println("END");

        if(finished(location)){
            System.out.println(score);
            if(score < lowScore){
                lowScore = score;
            }
            return;
        }

        if(level < 4)
            System.out.println(level);

        for(int j = 0; j < amp.size(); j++){
            var amphipod = amp.get(j);
            boolean inRoom = isInRoom(amphipod.getValue());
            ArrayList<Integer> arr = new ArrayList<>();
            validLocations(amphipod.getKey(), inRoom, arr, amphipod.getValue(), location);

            if(level < 3)
                System.out.println(level + " " + amphipod.getKey() + " " + amphipod.getValue() + " " + arr);

            for(int i : arr){
                Pair<Character, Integer> a = new Pair<>(amphipod.getKey(), i);
                ArrayList<Pair<Character, Integer>> ampCopy = (ArrayList<Pair<Character, Integer>>) amp.clone();
                ampCopy.set(j, a);
                int s = calculateCost(amphipod.getValue(), i, amphipod.getKey());
                if(score+s < lowScore)
                    traverse(ampCopy, score+s, level+1);
            }
        }
    }



    private static int calculateCost(int a, int b, char type){
        int cost = 0;

        HashMap<Integer, Boolean> visited = new HashMap<>();
        cost = goThroughGraph(visited, a, b, cost);

        if(type == 'A'){
            return cost;
        }else if(type == 'B'){
            return cost*10;
        }else if(type == 'C'){
            return cost*100;
        }else{
            return cost*1000;
        }
    }

    private static int goThroughGraph(HashMap<Integer, Boolean> visited, int a, int b, int cost){
        if(a == b){
            return cost+1;
        }
        HashMap<Integer, Boolean> visitedCopy = (HashMap<Integer, Boolean>) visited.clone();
        visitedCopy.put(a, true);

        var g = graph.get(a);
        for(int i :g){
            if(!visitedCopy.containsKey(i)){
                int x = goThroughGraph(visitedCopy, i, b, cost+1);
                if(x > 0){
                    return x;
                }
            }
        }

        return 0;
    }



    private static boolean finished(HashMap<Integer, Character> locations){
        if(locations.containsKey(2) && locations.get(2) == 'A'
                && locations.containsKey(3) && locations.get(3) == 'A'
                && locations.containsKey(5) && locations.get(5) == 'B'
                && locations.containsKey(6) && locations.get(6) == 'B'
                && locations.containsKey(8) && locations.get(8) == 'C'
                && locations.containsKey(9) && locations.get(9) == 'C'
                && locations.containsKey(11) && locations.get(11) == 'D'
                && locations.containsKey(12) && locations.get(12) == 'D'
        ){
            return true;
        }
        return false;
    }

    private static boolean isInRoom(int i){
       return i == 2 || i == 3 || i == 5 || i == 6 || i == 8 || i == 9 || i == 11 || i == 12;
    }

    private static boolean validRoomDestination(int destination, char type){
        return (type == 'A' && (destination == 2 || destination == 3)) ||
                (type == 'B' && (destination == 5 || destination == 6)) ||
                (type == 'C' && (destination == 8 || destination == 9)) ||
                (type == 'D' && (destination == 11 || destination == 12));
    }

    private static boolean validLocation(boolean inRoom, int i, char type){
        if(!inRoom && !isInRoom(i)) { //source and destination cant be both hallway
            return false;
        }

        //destination is a room but not the right room
        if(isInRoom(i) && !validRoomDestination(i, type)){
            return false;
        }

        return true;
    }

    private static boolean inGoodPlace(char type, int i, HashMap<Integer, Character> locations){
        if(i == 3 && type == 'A')
            return true;
        if(i == 2 && type == 'A' && locations.containsKey(3) && locations.get(3) == 'A')
            return true;
        if(i == 6 && type == 'B')
            return true;
        if(i == 5 && type == 'B' && locations.containsKey(6) && locations.get(6) == 'B')
            return true;
        if(i == 9 && type == 'C')
            return true;
        if(i == 8 && type == 'C' && locations.containsKey(9) && locations.get(9) == 'C')
            return true;
        if(i == 12 && type == 'D')
            return true;
        if(i == 11 && type == 'D' && locations.containsKey(12) && locations.get(12) == 'D')
            return true;
        return false;
    }

    private static void validLocations(char type, boolean inRoom, ArrayList<Integer> arr, int a, HashMap<Integer, Character> locations){
//        System.out.println(locations);

        if(inGoodPlace(type, a, locations))
            return;

        if(type == 'A'){
            if(!locations.containsKey(3) && !locations.containsKey(2)){
                arr.add(3);
                return;
            }else if(!locations.containsKey(2) && locations.containsKey(3) && locations.get(3) == 'A'){
                arr.add(2);
                return;
            }
        }
        if(type == 'B'){
            if(!locations.containsKey(5) && !locations.containsKey(6)){
                arr.add(6);
                return;
            }else if(!locations.containsKey(5) && locations.containsKey(6) && locations.get(6) == 'B'){
                arr.add(5);
                return;
            }
        }
        if(type == 'C'){
            if(!locations.containsKey(8) && !locations.containsKey(9)){
                arr.add(9);
                return;
            }else if(!locations.containsKey(8) && locations.containsKey(9) && locations.get(9) == 'C'){
                arr.add(8);
                return;
            }
        }
        if(type == 'D'){
            if(!locations.containsKey(11) && !locations.containsKey(12)){
                arr.add(12);
                return;
            }else if(!locations.containsKey(11) && locations.containsKey(12) && locations.get(12) == 'D'){
                arr.add(11);
                return;
            }
        }

        if(inRoom){
            if(!inGoodPlace(type, a, locations)){
                if(!locations.containsKey(0))
                    arr.add(0);
                if(!locations.containsKey(1))
                    arr.add(1);
                if(!locations.containsKey(4))
                    arr.add(4);
                if(!locations.containsKey(7))
                    arr.add(7);
                if(!locations.containsKey(10))
                    arr.add(10);
                if(!locations.containsKey(13))
                    arr.add(13);
                if(!locations.containsKey(14))
                    arr.add(14);
            }
        }
    }

    private static void setup(){
        amphipods.add(new Pair<>('B', 2));
        amphipods.add(new Pair<>('C', 3));
        amphipods.add(new Pair<>('B', 5));
        amphipods.add(new Pair<>('A', 6));
        amphipods.add(new Pair<>('D', 8));
        amphipods.add(new Pair<>('D', 9));
        amphipods.add(new Pair<>('A', 11));
        amphipods.add(new Pair<>('C', 12));

        /*
         0 1 x1 4 x2 7 x3 10 x4 13 14
              2    5    8    11
              3    6    9    12
         */

        graph.add(new ArrayList<>(Arrays.asList(1)));    //0
        graph.add(new ArrayList<>(Arrays.asList(0,15)));  //1
        graph.add(new ArrayList<>(Arrays.asList(3,15)));//2
        graph.add(new ArrayList<>(Arrays.asList(2)));    //3
        graph.add(new ArrayList<>(Arrays.asList(15,16)));  //4
        graph.add(new ArrayList<>(Arrays.asList(16, 6)));//5
        graph.add(new ArrayList<>(Arrays.asList(5)));    //6
        graph.add(new ArrayList<>(Arrays.asList(16,17)));  //7
        graph.add(new ArrayList<>(Arrays.asList(9,17)));//8
        graph.add(new ArrayList<>(Arrays.asList(8)));    //9
        graph.add(new ArrayList<>(Arrays.asList(17,18))); //10
        graph.add(new ArrayList<>(Arrays.asList(18,12))); //11
        graph.add(new ArrayList<>(Arrays.asList(11)));   //12
        graph.add(new ArrayList<>(Arrays.asList(18,14))); //13
        graph.add(new ArrayList<>(Arrays.asList(13)));   //14
        graph.add(new ArrayList<>(Arrays.asList(1,2,4)));   //x1 15
        graph.add(new ArrayList<>(Arrays.asList(4,5,7)));   //x2 16
        graph.add(new ArrayList<>(Arrays.asList(7,8,10)));   //x3 17
        graph.add(new ArrayList<>(Arrays.asList(10,11,13)));   //x4 18

    }
    private static void printHouse(HashMap<Integer, Character> locations){
        if(locations.containsKey(0)){
            System.out.print(locations.get(0));
        }else{
            System.out.print(".");
        }

        if(locations.containsKey(1)){
            System.out.print(locations.get(1));
        }else{
            System.out.print(".");
        }

        System.out.print(".");

        if(locations.containsKey(4)){
            System.out.print(locations.get(4));
        }else{
            System.out.print(".");
        }

        System.out.print(".");

        if(locations.containsKey(7)){
            System.out.print(locations.get(7));
        }else{
            System.out.print(".");
        }

        System.out.print(".");

        if(locations.containsKey(10)){
            System.out.print(locations.get(10));
        }else{
            System.out.print(".");
        }


        System.out.print(".");

        if(locations.containsKey(13)){
            System.out.print(locations.get(13));
        }else{
            System.out.print(".");
        }
        if(locations.containsKey(14)){
            System.out.print(locations.get(14));
        }else{
            System.out.print(".");
        }

        System.out.println();

        System.out.print("  ");
        if(locations.containsKey(2)){
            System.out.print(locations.get(2));
        }else{
            System.out.print(".");
        }

        System.out.print(" ");
        if(locations.containsKey(5)){
            System.out.print(locations.get(5));
        }else{
            System.out.print(".");
        }

        System.out.print(" ");
        if(locations.containsKey(8)){
            System.out.print(locations.get(8));
        }else{
            System.out.print(".");
        }

        System.out.print(" ");
        if(locations.containsKey(11)){
            System.out.print(locations.get(11));
        }else{
            System.out.print(".");
        }
        System.out.println();

        System.out.print("  ");
        if(locations.containsKey(3)){
            System.out.print(locations.get(3));
        }else{
            System.out.print(".");
        }

        System.out.print(" ");
        if(locations.containsKey(6)){
            System.out.print(locations.get(6));
        }else{
            System.out.print(".");
        }

        System.out.print(" ");
        if(locations.containsKey(9)){
            System.out.print(locations.get(9));
        }else{
            System.out.print(".");
        }

        System.out.print(" ");
        if(locations.containsKey(12)){
            System.out.print(locations.get(12));
        }else{
            System.out.print(".");
        }

        System.out.println();

    }
}
