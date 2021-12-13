package com.company;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day12 {

    private static HashMap<String, ArrayList<String>> caves = new HashMap<>();
    private static ArrayList<ArrayList<String>> allPaths = new ArrayList<>();
    private static int count = 0;
    private static boolean alreadyVisitedSmall = false;
    private static String selectedSmall = "";

    public static void Day12(){

        fileIO();

        partOne();
        partTwo();
    }

    private  static void partTwo(){
        System.out.println(caves);

        ArrayList<String> path = new ArrayList<>();;
        HashMap<String, Boolean> visitedCave = new HashMap<>();

        ArrayList<String> smallCaves = new ArrayList<>();
        for(var key: caves.keySet()){
            if(!isUpperCase(key)){
                smallCaves.add(key);
            }
        }

        traverseCaves2("start", path, visitedCave, false);

        System.out.println(allPaths.size());
        System.out.println(visitedCave);
    }

    private static void traverseCaves2(String cave, ArrayList<String> path, HashMap<String, Boolean> visitedCave, boolean visited){
        var paths = caves.get(cave);

        ArrayList<String> newPath = (ArrayList<String>) path.clone();
        newPath.add(cave);

        HashMap<String, Boolean> newVisited = (HashMap<String, Boolean>) visitedCave.clone();

        if(cave.equals("end")){
//            if(visited){
                count++;
                System.out.println(count + " "+ newPath);
//            }

            return;
        }

        if(!isUpperCase(cave)){
            newVisited.put(cave, true);
        }



        for(int i = 0; i < paths.size(); i++){
            String newCave = paths.get(i);
            boolean visitedCopy = visited;
            boolean canTravel = true;
            if(!isUpperCase(newCave)){
                if(newVisited.containsKey(newCave)){
                    canTravel = false;
                    if(!visitedCopy && !newCave.equals("start")){
                        canTravel = true;
                        visitedCopy = true;
                    }
                }

            }

//            boolean canTravel = isUpperCase(newCave) ? true : !newVisited.containsKey(newCave);
            if(newCave.equals("start")){
                canTravel = false;
            }
            if(canTravel){
                traverseCaves2(newCave, newPath, newVisited, visitedCopy);
            }
        }
    }

    private static void partOne(){
        System.out.println(caves);

        ArrayList<String> path = new ArrayList<>();;
        HashMap<String, Boolean> visitedCave = new HashMap<>();

        traverseCave("start", path, visitedCave);

        for(var arr: allPaths){
            System.out.println(arr);
        }
        System.out.println(allPaths.size());
        System.out.println(visitedCave);
    }

    private static void traverseCave(String cave, ArrayList<String> path, HashMap<String, Boolean> visitedCave){
        var paths = caves.get(cave);

        ArrayList<String> newPath = (ArrayList<String>) path.clone();
        newPath.add(cave);

        HashMap<String, Boolean> newVisited = (HashMap<String, Boolean>) visitedCave.clone();

        if(cave.equals("end")){
            allPaths.add(path);
            return;
        }

        if(!isUpperCase(cave)){
            newVisited.put(cave, true);
        }


        for(int i = 0; i < paths.size(); i++){
            String newCave = paths.get(i);
            boolean canTravel = isUpperCase(newCave) ? true : !newVisited.containsKey(newCave);
            if(newCave.equals("start")){
                canTravel = false;
            }
            if(canTravel){
                traverseCave(newCave, newPath, newVisited);
            }
        }
    }

    private static boolean isUpperCase(String s){
        return Character.isUpperCase(s.charAt(0));
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day12Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String arr[] = data.split("-");
                addLink(arr[0], arr[1]);
                addLink(arr[1], arr[0]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void addLink(String a, String b){
        if(caves.containsKey(a)){
            var list = caves.get(a);
            list.add(b);
            caves.put(a, list);
        }else{
            ArrayList<String> list = new ArrayList<>();
            list.add(b);
            caves.put(a, list);
        }
    }
}
