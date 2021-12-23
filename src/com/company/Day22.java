package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Instruction {
    public String command;
    public int minX = 0;
    public int maxX = 0;
    public int minY = 0;
    public int maxY = 0;
    public int minZ = 0;
    public int maxZ = 0;

    Instruction(){

    }

    Instruction(String command, int minX, int maxX, int minY, int maxY, int minZ, int maxZ){
        this.command = command;
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }
}

public class Day22 {

    private static ArrayList<Instruction> instructions = new ArrayList<>();

    private static ArrayList<Instruction> cubes = new ArrayList<>();

    private static HashMap<String, Boolean> grid = new HashMap<>();

    public static void Day22(){

        fileIO();


        setAllTo50();
//        printInstructions();
        partTwo();
        partOne();
    }

    private static void setAllTo50(){
        int min = -4;
        int max = 30;
        ArrayList<Instruction> iCopy = new ArrayList<>();
        for(int i = 0; i < instructions.size(); i++){
            var cube = instructions.get(i);
            if(cube.minX <= min) cube.minX = min;
            if(cube.maxX >= max) cube.maxX = max;
            if(cube.minY <= min) cube.minY = min;
            if(cube.maxY >= max) cube.maxY = max;
            if(cube.minZ <= min) cube.minZ = min;
            if(cube.maxZ >= max) cube.maxZ = max;

            if(cube.maxX >= min && cube.minX <= max && cube.maxY >= min && cube.minY <= max && cube.maxZ >= min && cube.minZ <= max)
            {
                System.out.println(i);
                printCube(cube);
                iCopy.add(cube);
            }
        }
        instructions = iCopy;
    }

    private static void partTwo(){
        int count = 0;
        for(var instruction: instructions){
            ArrayList<Instruction> newCubes = new ArrayList<>();
            for(var cube : cubes){
//                System.out.println("(" + instruction.minX + "," + instruction.maxX +"),("+instruction.minY + "," + instruction.maxY + "),(" +instruction.minZ + "," + instruction.maxZ + ")");
                subtractCubes(cube, instruction, newCubes);
            }
            if(instruction.command.equals("on")){
                newCubes.add(instruction);
            }
            cubes = newCubes;
            count++;
        }
//        System.out.println(cubes.size());
        long area = 0;
        for(var cube : cubes){
            long a = (Math.abs(cube.maxX-cube.minX)+1)*(Math.abs(cube.maxY-cube.minY)+1)*(Math.abs(cube.maxZ-cube.minZ)+1);
//            printCube(cube);
//            System.out.println(a);
            area += a;
//          System.out.println((Math.abs(cube.maxX-cube.minX)+1)*(Math.abs(cube.maxY-cube.minY)+1)*(Math.abs(cube.maxZ-cube.minZ)+1) + " " + a);
        }
        System.out.println(area);
    }

    private static void printCube(Instruction instruction){

                System.out.println("(" + instruction.minX + "," + instruction.maxX +"),("+instruction.minY + "," + instruction.maxY + "),(" +instruction.minZ + "," + instruction.maxZ + ")");
    }

    //subtract cubeA from cubeB and add the resulting cubes in newCubes
    private static void subtractCubes(Instruction cubeA, Instruction cubeB, ArrayList<Instruction> newCubes){

        //cube b 'fits' over a
        if(cubeB.minX <= cubeA.minX && cubeB.maxX >= cubeA.maxX
                && cubeB.minY <= cubeA.minY && cubeB.maxY >= cubeA.maxY
                && cubeB.minZ <= cubeA.minZ && cubeB.maxZ >= cubeA.maxZ){
            return;
        }

        //whole of cube a is outside of b
        if(cubeB.maxX < cubeA.minX || cubeB.minX > cubeA.maxX
                || cubeB.maxY < cubeA.minY || cubeB.minY > cubeA.maxY
                || cubeB.maxZ < cubeA.minZ || cubeB.minZ > cubeA.maxZ){
            newCubes.add(cubeA);
            return;
        }

        Instruction smallCube = new Instruction("on", Math.max(cubeA.minX, cubeB.minX), Math.min(cubeA.maxX, cubeB.maxX), Math.max(cubeA.minY, cubeB.minY), Math.min(cubeA.maxY, cubeB.maxY), Math.max(cubeA.minZ, cubeB.minZ), Math.min(cubeA.maxZ, cubeB.maxZ));

//        System.out.println("[(" + smallCube.minX + "," + smallCube.maxX + "),(" + cubeA.minX + "," + cubeA.maxX +")]" +
//                "[(" + smallCube.minY + "," + smallCube.maxY + "),(" + cubeA.minY + "," + cubeA.maxY +")]" +
//                "[(" + smallCube.minZ + "," + smallCube.maxZ + "),(" + cubeA.minZ + "," + cubeA.maxZ +")]");

        if(smallCube.minZ != cubeA.minZ){
            Instruction frontCube = new Instruction("on", cubeA.minX, cubeA.maxX, cubeA.minY, cubeA.maxY, cubeA.minZ, smallCube.minZ-1);
            newCubes.add(frontCube);
//            System.out.println("adding front (" + frontCube.minX + "," + frontCube.maxX +"),("+frontCube.minY + "," + frontCube.maxY + "),(" +frontCube.minZ + "," + frontCube.maxZ + ")");
        }

        if(smallCube.maxZ != cubeA.maxZ){
            Instruction backCube = new Instruction("on", cubeA.minX, cubeA.maxX, cubeA.minY, cubeA.maxY, smallCube.maxZ+1, cubeA.maxZ);
            newCubes.add(backCube);
//            System.out.println("adding back (" + backCube.minX + "," + backCube.maxX +"),("+backCube.minY + "," + backCube.maxY + "),(" +backCube.minZ + "," + backCube.maxZ + ")");
        }

        if(smallCube.minX != cubeA.minX){
            Instruction sideLeftCube = new Instruction("on", cubeB.minX, smallCube.minX-1, cubeA.minY, cubeA.maxY, smallCube.minZ, smallCube.maxZ);
            newCubes.add(sideLeftCube);
//            System.out.println("adding side left (" + sideLeftCube.minX + "," + sideLeftCube.maxX +"),("+sideLeftCube.minY + "," + sideLeftCube.maxY + "),(" +sideLeftCube.minZ + "," + sideLeftCube.maxZ + ")");
        }

        if(smallCube.maxX != cubeA.maxX){
            Instruction sideRightCube = new Instruction("on", smallCube.maxX+1, cubeA.maxX, cubeA.minY, cubeA.maxY, smallCube.minZ, smallCube.maxZ);
            newCubes.add(sideRightCube);
//            System.out.println("adding side right (" + sideRightCube.minX + "," + sideRightCube.maxX +"),("+sideRightCube.minY + "," + sideRightCube.maxY + "),(" +sideRightCube.minZ + "," + sideRightCube.maxZ + ")" );
        }

        if(smallCube.maxY != cubeA.maxY){
            Instruction topCube = new Instruction("on", smallCube.minX, smallCube.maxX, smallCube.maxY+1, cubeA.maxY, smallCube.minZ, smallCube.maxZ);
            newCubes.add(topCube);
//            System.out.println("adding top (" + topCube.minX + "," + topCube.maxX +"),("+topCube.minY + "," + topCube.maxY + "),(" +topCube.minZ + "," + topCube.maxZ + ")");
        }

        if(smallCube.minY != cubeA.minY){
            Instruction bottomCube = new Instruction("on", smallCube.minX, smallCube.maxX, cubeA.minY, smallCube.minY-1, smallCube.minZ, smallCube.maxZ);
            newCubes.add(bottomCube);
//            System.out.println("adding bottom (" + bottomCube.minX + "," + bottomCube.maxX +"),("+bottomCube.minY + "," + bottomCube.maxY + "),(" +bottomCube.minZ + "," + bottomCube.maxZ + ")");
        }
    }

    private static void partOne(){
        for(var instruction : instructions){
            int minX =  instruction.minX; // >= -50 ? instruction.minX : -50;
            int maxX = instruction.maxX;// <= 50 ? instruction.maxX : 50;
            int minY = instruction.minY;// >= -50 ? instruction.minY : -50;
            int maxY = instruction.maxY;// <= 50 ? instruction.maxY : 50;
            int minZ = instruction.minZ;// >= -50 ? instruction.minZ : -50;
            int maxZ = instruction.maxZ;// <= 50 ? instruction.maxZ : 50;
            for(int i = minX; i <= maxX; i++){
                for(int j = minY; j <= maxY; j++){
                    for(int k = minZ; k <= maxZ; k++){
                        if(i >= -50 && i <= 50 && j >= -50 && j <= 50 && k >= -50 && k <= 50){
                            String key = i+","+j+","+k;
                            if(instruction.command.equals("on")){
                                grid.put(key, true);
                            }else{
                                if(grid.containsKey(key)){
                                    grid.put(key, false);
                                }
                            }
                        }
                    }
                }
            }
        }

        long count = 0;
        for(var key: grid.keySet()){
            if(grid.get(key)){
                count++;
            }
        }
        System.out.println(count);
    }

    public static void printInstructions(){
        for(var instruction : instructions){
            System.out.println(instruction.command + " " + instruction.minX + "-" + instruction.maxX + " " + instruction.minY + "-" + instruction.maxY + " " + instruction.minZ + "-" + instruction.maxZ);
        }
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day22Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String arr[] = data.split(" ");
                Instruction instruction = new Instruction();
                instruction.command = arr[0];
                arr = arr[1].split(",");
                var x = arr[0].split("=")[1].split("\\.\\.");
                var y = arr[1].split("=")[1].split("\\.\\.");
                var z = arr[2].split("=")[1].split("\\.\\.");
                instruction.minX = Integer.parseInt(x[0]);
                instruction.maxX = Integer.parseInt(x[1]);
                instruction.minY = Integer.parseInt(y[0]);
                instruction.maxY = Integer.parseInt(y[1]);
                instruction.minZ = Integer.parseInt(z[0]);
                instruction.maxZ = Integer.parseInt(z[1]);
                instructions.add(instruction);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
