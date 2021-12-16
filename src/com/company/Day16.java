package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

class Section {
    public int version = 0;
    public int type = 0;
    public ArrayList<Section> subPackets = new ArrayList<>();
    long value = 0;

    Section(int version, int type){
        this.version = version;
        this.type = type;
    }

}

public class Day16 {

    private static String binary = "";
    private static ArrayList<Section> sections = new ArrayList<>();
    private static int count = 0;

    public static void Day16(){
        fileIO();

        partOne();


    }

    private static void partOne(){

        System.out.println(binary);
        count = 0;
        sections.add(goThrough());
        int sum = 0;
        for(var s : sections){
            printSections("", s);
            sum += calulateSum(s);
        }

        System.out.println(sum);

    }

    private static int calulateSum(Section section){
        int sum = section.version;
        for(var s : section.subPackets){
            sum += calulateSum(s);
        }
        return sum;
    }

    private static void printSections(String space, Section section){
        System.out.println(space + "version " + section.version);
        System.out.println(space + "type " + section.type);
        System.out.println(space + "value " + section.value);
        for(var s : section.subPackets){
            printSections(space + "-", s);
        }

    }

    private static Section goThrough(){

        int version = Integer.parseInt(binary.substring(count, count+3), 2);
        count += 3;
        int type = Integer.parseInt(binary.substring(count,count+3),2);
        System.out.println("type: " + type);
        count += 3;
        Section section = new Section(version, type);
        if(type == 4){
            boolean reachedEnd = false;
            String val = "";
            int c = 0;
            while(!reachedEnd){
                String v = binary.substring(count, count+5);
                count+=5;
                if(v.charAt(0) == '0'){
                    reachedEnd = true;
                }
                val += v.substring(1,5);
                c += 4;
            }
//            count+=roundToMultipleOfFour(c);
            long value = Long.parseLong(val, 2);
            section.value = value;
            System.out.println("value: " + value);
            return section;
        }else{
            char I = binary.charAt(count);
            count++;
            if(I == '0'){
                int length = Integer.parseInt(binary.substring(count, count+15),2);
                System.out.println("lengthOne: " + length);
                count += 15;
                int goal = count + length;
                ArrayList<Section> sections = new ArrayList<>();
                while(count < goal){
                    sections.add(goThrough());
                }
                section.subPackets = sections;

            }else{
                int length = Integer.parseInt(binary.substring(count, count+11),2);
                System.out.println("lengthTwo: " + length);
                count += 11;
                ArrayList<Section> sections = new ArrayList<>();
                for(int i = 0; i < length; i++){
                    sections.add(goThrough());
                }
                section.subPackets = sections;
            }

            return section;
        }
    }

    private static int roundToMultipleOfFour(int x){
        int i = 0;
        while(true){
            if(Math.pow(i,4) < x && Math.pow(i+1,4) > x){
                return (int) Math.pow(i+1,4);
            }
        }
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day16Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                binary = new BigInteger(data, 16).toString(2);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
