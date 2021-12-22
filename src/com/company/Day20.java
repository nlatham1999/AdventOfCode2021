package com.company;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day20 {

    private static String enhancementString = "";
    private static ArrayList<ArrayList<Character>> image = new ArrayList<>();
    private static char infiniteChar = '.';

    public static void Day20(){

        fileIO();

        partOne();
    }

    private static void partOne(){
        printImage();
        System.out.println();
        for(int i = 0; i < 50; i++){
            paddImage(i);

            String num = "";
            for(int j = 0; j < 9; j++){
                num += infiniteChar;
            }

            infiniteChar = convertToChar(num);

            newImage();

            System.out.println(i);
        }

        int count = 0;
        for(int i = 0; i < image.size(); i++){
            for(int j = 0; j < image.get(i).size(); j++){
                if(image.get(i).get(j) == '#'){
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void printImage(){
        for(int i = 0; i < image.size(); i++){
            for(int j = 0; j < image.get(i).size(); j++){
                System.out.print(image.get(i).get(j));
            }
            System.out.println();
        }
    }

    private static void newImage(){
        ArrayList<ArrayList<Character>> newImage = new ArrayList<>();

        for(int i = 0; i < image.size(); i++){
            ArrayList<Character> row = new ArrayList<>();
            var arr = image.get(i);
            for(int j = 0; j < arr.size(); j++){
                String number = "";

                if(i > 0 && j > 0){
                    number  += image.get(i-1).get(j-1);
                }else{
                    number += image.get(i).get(j);
                }

                if(i > 0){
                    number += image.get(i-1).get(j);
                }else{
                    number += image.get(i).get(j);
                }

                if(i > 0 && j < arr.size()-1){
                    number += image.get(i-1).get(j+1);
                }else{
                    number += image.get(i).get(j);
                }

                if(j > 0){
                    number += image.get(i).get(j-1);
                }else{
                    number += image.get(i).get(j);
                }

                number += image.get(i).get(j);

                if(j < arr.size()-1){
                    number += image.get(i).get(j+1);
                }else{
                    number += image.get(i).get(j);
                }

                if(i < image.size()-1 && j > 0){
                    number += image.get(i+1).get(j-1);
                }else{
                    number += image.get(i).get(j);
                }

                if(i < image.size()-1){
                    number += image.get(i+1).get(j);
                }else{
                    number += image.get(i).get(j);
                }

                if(i < image.size()-1 && j < arr.size()-1){
                    number += image.get(i+1).get(j+1);
                }else{
                    number += image.get(i).get(j);
                }

                row.add(convertToChar(number));
            }
            newImage.add(row);
        }
        image = newImage;
    }

    private static char convertToChar(String num){
        String binary = "";
        for(int i = 0; i < num.length(); i++){
            if(num.charAt(i) == '.'){
                binary += '0';
            }else{
                binary += '1';
            }
        }

        int number = Integer.parseInt(binary, 2);
        return enhancementString.charAt(number);
    }

    private static void paddImage(int i){
        int length = image.get(0).size();
        ArrayList<Character> topAndBottom = new ArrayList<>();
        for(int j = 0; j < length; j++){
            topAndBottom.add(infiniteChar);
        }
        image.add(0, (ArrayList<Character>) topAndBottom.clone());
        image.add((ArrayList<Character>) topAndBottom.clone());
        for(int j = 0; j < image.size(); j++){
            var arr = image.get(j);
            arr.add(0, infiniteChar);
            arr.add(infiniteChar);
        }

    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day20Input");
        try {
            Scanner reader = new Scanner(inputFile);
            int i = 0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if(i == 0){
                    enhancementString = data;
                }else if(i > 1){
                    ArrayList<Character> row = new ArrayList<>();
                    for(int j = 0; j < data.length(); j++){
                        row.add(data.charAt(j));
                    }
                    image.add(row);
                }
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
