package com.company;

import javax.naming.CompoundName;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day10 {

    private static ArrayList<String> lines = new ArrayList<>();
    private static ArrayList<ArrayList<Character>> openings = new ArrayList<>();

    public static void Day10(){

        fileIO();

        partOne();

        partTwo();
    }

    private  static void partTwo(){

        ArrayList<Long> scores = new ArrayList<>();
        for(var x: openings){
            System.out.println(x);

            Collections.reverse(x);
            long score = 0;
            for(var c: x){
                score *= 5;
                if(c == '('){
                    score += 1;
                }
                if(c == '{'){
                    score += 3;
                }
                if(c == '<'){
                    score += 4;
                }
                if(c == '['){
                    score += 2;
                }
            }
            scores.add(score);
        }

        Collections.sort(scores);

        for(int i = 0; i < scores.size(); i++){
            System.out.println(i + ": " + scores.get(i));
        }

        System.out.println(scores.get(scores.size()/2));
    }

    private static void partOne(){
        ArrayList<Character> errors = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            ArrayList<Character> stack = new ArrayList<>();
            boolean error = false;
            for(int j = 0; j < line.length() && !error; j++){
                char c = line.charAt(j);
                if(c == '(' || c == '<' || c == '{' || c == '['){
                    stack.add(c);
                }else if(doesMatch(getTop(stack), c)) {
                    stack.remove(stack.size()-1);
                }else {
                    errors.add(c);
                    error = true;
                }
            }
            if(!error){
                openings.add(stack);
            }
        }

        int sum = 0;
        for(var x : errors){
            if(x == ')'){
                sum += 3;
            }else if(x == '}'){
                sum += 1197;
            }else if(x == '>'){
                sum += 25137;
            }else{
                sum += 57;
            }
        }

        System.out.println(sum);
    }

    private static boolean doesMatch(char x, char y){
        return (x == '(' && y == ')') || (x == '{' && y == '}') || (x == '[' && y == ']') || (x == '<' && y == '>');
    }


    private static char getTop(ArrayList<Character> arr){
        return arr.get(arr.size()-1);
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day10Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                lines.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
