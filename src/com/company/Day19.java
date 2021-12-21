package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Day19 {

    public static ArrayList<ArrayList<Triplet>> field = new ArrayList<>();
    public static ArrayList<ArrayList<Triplet>> scanners = new ArrayList<>();

    public static HashMap<String, Integer> positions = new HashMap<>();
    public static ArrayList<Triplet> scannerPositions = new ArrayList<>();

    public static void Day19(){
        fileIO();

        partOne();

        partTwo();
    }

    private static void partTwo(){
        int maxDistance = 0;
        for(int i = 0; i < scannerPositions.size(); i++){
            for(int j = 0; j < scannerPositions.size(); j++){
                if(i != j){
                    Triplet a = scannerPositions.get(i);
                    Triplet b = scannerPositions.get(j);
                    int dist = Math.abs(a.first-b.first)+Math.abs(a.second-b.second)+Math.abs(a.third-b.third);
                    if(dist > maxDistance){
                        maxDistance = dist;
                        System.out.println(maxDistance + " : (" + a.first + "," + a.second + "," + a.third + ") + ("+ b.first + "," + b.second + "," + b.third +")");
                    }
                }
            }
        }
    }

    private static void partOne(){

        field.add(scanners.get(0));
        scanners.remove(0);

//        printRow(field.get(0));



        while(scanners.size() > 0) {
            System.out.println(scanners.size() + " " + field.size());
            goThroughScanners();
        }

        for(var arr : field){
            for(var triplet: arr){
                String key = triplet.first+","+triplet.second+","+triplet.third;
                if(!positions.containsKey(key)){
                    positions.put(key, 0);
                }
            }
        }
        System.out.println(positions);
    }

    private static void goThroughScanners(){
        for (int j = 0; j < scanners.size(); j++) {
            var scanner = scanners.get(j);
            boolean found = false;
            for (int i = 0; i < 48  && !found; i++) {
                var rotatedScanner = rotate(i, (ArrayList<Triplet>) scanner.clone());
                if(findMatch(rotatedScanner)){
                    found = true;
                    scanners.remove(j);
                    System.out.println(i);
                    return;
                }
            }
        }
    }

    private static void printRow(ArrayList<Triplet> list){
        for(var triplet : list){
            System.out.println(triplet.first + " " + triplet.second + " " + triplet.third);
        }
    }

    private static boolean findMatch(ArrayList<Triplet> rotatedScanner){
        Map<String, Integer> recorded = new HashMap<>();
        for(var setS : field){
            for(var t2 : setS){
                for(var t : rotatedScanner){
                    recorded.put(t.first+"-"+t.second+"-"+t.third,0);
                }
                for(var t : rotatedScanner){

//                    System.out.println("\noriginal\n");
//                    printRow(setS);

//                    System.out.println("\nscanner\n");
//                    printRow(rotatedScanner);
//                    System.out.println(recorded);

//                    System.out.println("COMPARING: (" + t2.first + " " + t2.second + " " + t2.third + ") -> (" + t.first + " " + t.second + " " + t.third + ")");
                    int offsetX = t2.first - t.first;
                    int offsetY = t2.second - t.second;
                    int offsetZ = t2.third - t.third;
                    int count = 0;
//                    System.out.println("\noffsets\n");
                    for(var t3 : setS){
                        String key = (t3.first-offsetX) + "-" + (t3.second-offsetY) + "-" + (t3.third-offsetZ);
//                        System.out.println(key);
                        if(recorded.containsKey(key)){
                            count++;
                        }
                        if(count >= 3){
                            ArrayList<Triplet> newB = new ArrayList<>();
                            for(var trip : rotatedScanner){
                                newB.add(new Triplet(trip.first+offsetX, trip.second+offsetY, trip.third+offsetZ));
                            }
                            field.add(newB);
                            scannerPositions.add(new Triplet(offsetX, offsetY, offsetZ));
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static  ArrayList<Triplet> rotate(int option, ArrayList<Triplet> arr){
        for(int i = 0; i < arr.size(); i++){
            Triplet t = arr.get(i);
            Triplet newT;
            switch (option){
                case 0:
                    newT = new Triplet(t.first, t.second, t.third);
                    break;
                case 1:
                    newT = new Triplet(t.first, t.third, t.second);
                    break;
                case 2:
                    newT = new Triplet(t.second, t.first, t.third);
                    break;
                case 3:
                    newT = new Triplet(t.second, t.third, t.first);
                    break;
                case 4:
                    newT = new Triplet(t.third, t.first, t.second);
                    break;
                case 5:
                    newT = new Triplet(t.third, t.second, t.first);
                    break;


                case 6:
                    newT = new Triplet(t.first, t.second, t.third*-1);
                    break;
                case 7:
                    newT = new Triplet(t.first, t.third, t.second*-1);
                    break;
                case 8:
                    newT = new Triplet(t.second, t.first, t.third*-1);
                    break;
                case 9:
                    newT = new Triplet(t.second, t.third, t.first*-1);
                    break;
                case 10:
                    newT = new Triplet(t.third, t.first, t.second*-1);
                    break;
                case 11:
                    newT = new Triplet(t.third, t.second, t.first*-1);
                    break;

                case 12:
                    newT = new Triplet(t.first, t.second*-1, t.third);
                    break;
                case 13:
                    newT = new Triplet(t.first, t.third*-1, t.second);
                    break;
                case 14:
                    newT = new Triplet(t.second, t.first*-1, t.third);
                    break;
                case 15:
                    newT = new Triplet(t.second, t.third*-1, t.first);
                    break;
                case 16:
                    newT = new Triplet(t.third, t.first*-1, t.second);
                    break;
                case 17:
                    newT = new Triplet(t.third, t.second*-1, t.first);
                    break;

                case 18:
                    newT = new Triplet(t.first, t.second*-1, t.third*-1);
                    break;
                case 19:
                    newT = new Triplet(t.first, t.third*-1, t.second*-1);
                    break;
                case 20:
                    newT = new Triplet(t.second, t.first*-1, t.third*-1);
                    break;
                case 21:
                    newT = new Triplet(t.second, t.third*-1, t.first*-1);
                    break;
                case 22:
                    newT = new Triplet(t.third, t.first*-1, t.second*-1);
                    break;
                case 23:
                    newT = new Triplet(t.third, t.second*-1, t.first*-1);
                    break;

                case 24:
                    newT = new Triplet(t.first*-1, t.second, t.third);
                    break;
                case 25:
                    newT = new Triplet(t.first*-1, t.third, t.second);
                    break;
                case 26:
                    newT = new Triplet(t.second*-1, t.first, t.third);
                    break;
                case 27:
                    newT = new Triplet(t.second*-1, t.third, t.first);
                    break;
                case 28:
                    newT = new Triplet(t.third*-1, t.first, t.second);
                    break;
                case 29:
                    newT = new Triplet(t.third*-1, t.second, t.first);
                    break;

                case 30:
                    newT = new Triplet(t.first*-1, t.second, t.third*-1);
                    break;
                case 31:
                    newT = new Triplet(t.first*-1, t.third, t.second*-1);
                    break;
                case 32:
                    newT = new Triplet(t.second*-1, t.first, t.third*-1);
                    break;
                case 33:
                    newT = new Triplet(t.second*-1, t.third, t.first*-1);
                    break;
                case 34:
                    newT = new Triplet(t.third*-1, t.first, t.second*-1);
                    break;
                case 35:
                    newT = new Triplet(t.third*-1, t.second, t.first*-1);
                    break;


                case 36:
                    newT = new Triplet(t.first*-1, t.second*-1, t.third);
                    break;
                case 37:
                    newT = new Triplet(t.first*-1, t.third*-1, t.second);
                    break;
                case 38:
                    newT = new Triplet(t.second*-1, t.first*-1, t.third);
                    break;
                case 39:
                    newT = new Triplet(t.second*-1, t.third*-1, t.first);
                    break;
                case 40:
                    newT = new Triplet(t.third*-1, t.first*-1, t.second);
                    break;
                case 41:
                    newT = new Triplet(t.third*-1, t.second*-1, t.first);
                    break;

                case 42:
                    newT = new Triplet(t.first*-1, t.second*-1, t.third*-1);
                    break;
                case 43:
                    newT = new Triplet(t.first*-1, t.third*-1, t.second*-1);
                    break;
                case 44:
                    newT = new Triplet(t.second*-1, t.first*-1, t.third*-1);
                    break;
                case 45:
                    newT = new Triplet(t.second*-1, t.third*-1, t.first*-1);
                    break;
                case 46:
                    newT = new Triplet(t.third*-1, t.first*-1, t.second*-1);
                    break;
                case 47:
                    newT = new Triplet(t.third*-1, t.second*-1, t.first*-1);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }
            arr.set(i, newT);
        }
        return arr;
    }

    private static  ArrayList<Triplet> rotate2(int option, ArrayList<Triplet> arr){
        for(int i = 0; i < arr.size(); i++){
            Triplet t = arr.get(i);
            Triplet newT;
            switch (option){
                case 0:
                    newT = new Triplet(t.first, t.second, t.third);
                    break;
                case 1:
                    newT = new Triplet(t.first, t.third, t.second*-1);
                    break;
                case 2:
                    newT = new Triplet(t.first, t.second*-1, t.third*-1);
                    break;
                case 3:
                    newT = new Triplet(t.first, t.third*-1, t.second);
                    break;
                case 4:
                    newT = new Triplet(t.first*-1, t.second, t.third*-1);
                    break;
                case 5:
                    newT = new Triplet(t.first*-1, t.third, t.second);
                    break;
                case 6:
                    newT = new Triplet(t.first*-1, t.second*-1, t.third);
                    break;
                case 7:
                    newT = new Triplet(t.first*-1, t.third*-1, t.second*-1);
                    break;
                case 8:
                    newT = new Triplet(t.second, t.first, t.third*-1);
                    break;
                case 9:
                    newT = new Triplet(t.second, t.third, t.first);
                    break;
                case 10:
                    newT = new Triplet(t.second, t.first*-1, t.third);
                    break;
                case 11:
                    newT = new Triplet(t.second, t.third, t.first*-1);
                    break;
                case 12:
                    newT = new Triplet(t.second*-1, t.first, t.third);
                    break;
                case 13:
                    newT = new Triplet(t.second*-1, t.third, t.first*-1);
                    break;
                case 14:
                    newT = new Triplet(t.second*-1, t.first*-1, t.third*-1);
                    break;
                case 15:
                    newT = new Triplet(t.second*-1, t.third*-1, t.first);
                    break;
                case 16:
                    newT = new Triplet(t.third, t.first, t.second);
                    break;
                case 17:
                    newT = new Triplet(t.third, t.second, t.first*-1);
                    break;
                case 18:
                    newT = new Triplet(t.third, t.first*-1, t.second*-1);
                    break;
                case 19:
                    newT = new Triplet(t.third, t.second*-1, t.first);
                    break;
                case 20:
                    newT = new Triplet(t.third*-1, t.first, t.second*-1);
                    break;
                case 21:
                    newT = new Triplet(t.third*-1, t.second, t.first);
                    break;
                case 22:
                    newT = new Triplet(t.third*-1, t.first*-1, t.second);
                    break;
                case 23:
                    newT = new Triplet(t.third*-1, t.second*-1, t.third*-1);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }
            arr.set(i, newT);
        }
        return arr;
    }

    private static void printScanners(){
        for(var scanner : scanners){
            System.out.println();
            for(var trip: scanner){
                System.out.println(trip.first + " " + trip.second + " " + trip.third);
            }
        }
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day19Input");
        try {
            Scanner reader = new Scanner(inputFile);
            ArrayList<Triplet> scanner = new ArrayList<>();
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if(data.contains("scanner")){
                    if(scanner.size() > 0){
                        scanners.add(scanner);
                        scanner = new ArrayList<>();
                    }
                }else if(!data.equals("")){
                    var arr = data.split(",");
                    Triplet trip = new Triplet(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                    scanner.add(trip);
                }
            }
            scanners.add(scanner);

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
