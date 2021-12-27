package com.company;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day24 {

    private static ArrayList<Pair<String, ArrayList<String>>> instructions = new ArrayList<>();

    public static void Day24(){

        fileIO();

        partOne();
    }

    private static void partOne(){

        for(long i = 999_999_999_999_99L; i > 1; i--){
//            System.out.println(i);
            String strNum = String.valueOf(i);
            if(!strNum.contains("0")){
                HashMap<String, Long> variables = new HashMap<>();
                variables.put("w", (long) 0);
                variables.put("x", (long) 0);
                variables.put("y", (long) 0);
                variables.put("z", (long) 0);
                int count = 0;
                for(var instruction : instructions){
                    if(instruction.getKey().equals("inp")){
                        variables.put(instruction.getValue().get(0),  Long.parseLong(String.valueOf(strNum.charAt(count))));
                        count++;
                    }else if(instruction.getKey().equals("add")){
                        String a = instruction.getValue().get(0);
                        String b = instruction.getValue().get(1);
                        if(isInteger(b)){
                            variables.put(a, variables.get(a)+Integer.parseInt(b));
                        }else{
                            variables.put(a, variables.get(a)+variables.get(b));
                        }
                    }else if(instruction.getKey().equals("mul")){
                        String a = instruction.getValue().get(0);
                        String b = instruction.getValue().get(1);
                        if(isInteger(b)){
                            variables.put(a, variables.get(a)*Integer.parseInt(b));
                        }else{
                            variables.put(a, variables.get(a)*variables.get(b));
                        }
                    }else if(instruction.getKey().equals("div")){
                        String a = instruction.getValue().get(0);
                        String b = instruction.getValue().get(1);
                        if(isInteger(b)){
                            variables.put(a, variables.get(a)/Integer.parseInt(b));
                        }else{
                            variables.put(a, variables.get(a)/variables.get(b));
                        }
                    }else if(instruction.getKey().equals("mod")){
                        String a = instruction.getValue().get(0);
                        String b = instruction.getValue().get(1);
                        if(isInteger(b)){
                            variables.put(a, variables.get(a)%Integer.parseInt(b));
                        }else{
                            variables.put(a, variables.get(a)%variables.get(b));
                        }
                    }else if(instruction.getKey().equals("eql")){
                        String a = instruction.getValue().get(0);
                        String b = instruction.getValue().get(1);
                        if(isInteger(b)){
                            if(variables.get(a) == Integer.parseInt(b)){
                                variables.put(a, (long) 1);
                            }else{
                                variables.put(a, (long) 0);
                            }
                        }else{
                            if(variables.get(a) == variables.get(b)){
                                variables.put(a, (long) 1);
                            }else{
                                variables.put(a, (long) 0);
                            }
                        }
                    }
                }
                if(variables.get("z") == 0){
                    System.out.println(i);
                    return;
                }
            }
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day24Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String arr[] = data.split(" ");
                ArrayList<String> row = new ArrayList<>();
                row.add(arr[1]);
                if(arr.length > 2){
                    row.add(arr[2]);
                }
                instructions.add(new Pair<>(arr[0], row));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
