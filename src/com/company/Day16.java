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

        partTwo();
    }

    private static void partTwo(){
        System.out.println(travelThroughPackets(sections.get(0)));
    }

    private static long travelThroughPackets(Section section){
        int type = section.type;
        if(type == 0){
            long sum = 0;
            for(var s : section.subPackets){
                sum += travelThroughPackets(s);
            }
            return sum;
        }else if(type == 1){
            long prod = 1;
            for(var s : section.subPackets){
                prod *= travelThroughPackets(s);
            }
            return prod;
        }else if(type == 2){
            long min = travelThroughPackets(section.subPackets.get(0));
            for(var s : section.subPackets){
                long x = travelThroughPackets(s);
                if(x < min){
                    min = x;
                }
            }
            return min;
        }else if(type == 3){
            long max = travelThroughPackets(section.subPackets.get(0));
            for(var s : section.subPackets){
                long x = travelThroughPackets(s);
                if(x > max){
                    max = x;
                }
            }
            return max;
        }else if(type == 4){
            return section.value;
        }else if(type == 5){
            long first = travelThroughPackets(section.subPackets.get(0));
            long second = travelThroughPackets(section.subPackets.get(1));
            if( first > second){
                return 1;
            }else{
                return 0;
            }

        }else if(type == 6){
            long first = travelThroughPackets(section.subPackets.get(0));
            long second = travelThroughPackets(section.subPackets.get(1));
            if( first < second){
                return 1;
            }else{
                return 0;
            }

        }else{
            if(travelThroughPackets(section.subPackets.get(0)) == travelThroughPackets(section.subPackets.get(1))){
                return 1;
            }else{
                return 0;
            }
        }
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
            return section;
        }else{
            char I = binary.charAt(count);
            count++;
            if(I == '0'){
                int length = Integer.parseInt(binary.substring(count, count+15),2);
                count += 15;
                int goal = count + length;
                ArrayList<Section> sections = new ArrayList<>();
                while(count < goal){
                    sections.add(goThrough());
                }
                section.subPackets = sections;

            }else{
                int length = Integer.parseInt(binary.substring(count, count+11),2);
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
