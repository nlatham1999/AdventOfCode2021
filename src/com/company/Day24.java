package com.company;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day24 {

    private static ArrayList<Pair<String, ArrayList<String>>> instructions = new ArrayList<>();

    private static ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    private static ArrayList<Integer> b = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    private static ArrayList<Integer> c = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    private static ArrayList<Integer> d = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    private static ArrayList<Integer> e = new ArrayList<>(Arrays.asList(5,6,7,8,9));
    private static ArrayList<Integer> f = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
    private static ArrayList<Integer> g = new ArrayList<>(Arrays.asList(3,4,5,6,7,8,9));
    private static ArrayList<Integer> h = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    private static ArrayList<Integer> i = new ArrayList<>(Arrays.asList(9));
    private static ArrayList<Integer> j = new ArrayList<>(Arrays.asList(1));
    private static ArrayList<Integer> l = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    private static ArrayList<Integer> k = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    private static ArrayList<Integer> m = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    private static ArrayList<Integer> n = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));


    public static void Day24(){

        fileIO();

        partOne();
    }

    private static void partOne(){

        goThrough();
//        matchCriteria();
//        tester();
    }

    private static void goThrough(){
        for(var ai : a) {
            for (var bi : b) {
                for (var ci : c) {
                    for (var di : d) {
                        System.out.println(ai + " " + bi + " " + ci + " " + di);
                        for (var ei : e) {
                            if((((((((ai+15)*26)+(bi+10))*26)+(ci+2))*26)+(di+16))%26-12==ei) {
                                for (var fi : f) {
                                    for (var gi : g) {
                                        if(((((((((((ai+15)*26)+(bi+10))*26)+(ci+2))*26)+(di+16))/26)*26)+(fi+11))%26-9==gi) {
                                            for (var hi : h) {
                                                for (var ii : i) {
                                                    for (var ji : j) {
                                                        for (var ki : k) {
                                                            for (var li : l) {
                                                                for (var mi : m) {
                                                                    for (var ni : n) {

                                                                        check(ai,bi,ci,di,ei,fi,gi,hi,ii,ji,ki,li,mi,ni);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void check(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j, int k, int l, int m, int n){
        int z = -1;
        if((((((((a+15)*26)+(b+10))*26)+(c+2))*26)+(d+16))%26-12==e){
            if(((((((((((a+15)*26)+(b+10))*26)+(c+2))*26)+(d+16))/26)*26)+(f+11))%26-9==g){
                if((((((((((((((((a+15)*26)+(b+10))*26)+(c+2))*26)+(d+16))/26)*26)+(f+11))/26)*26)+(h+16))*26)+(i+6))%26-14==j){
                    if(((((((((((((((((a+15)*26)+(b+10))*26)+(c+2))*26)+(d+16))/26)*26)+(f+11))/26)*26)+(h+16))*26)+(i+6))/26)%26-11==k){
                        if((((((((((((((((((a+15)*26)+(b+10))*26)+(c+2))*26)+(d+16))/26)*26)+(f+11))/26)*26)+(h+16))*26)+(i+6))/26)/26)%26-2==l){
                            if(((((((((((((((((((a+15)*26)+(b+10))*26)+(c+2))*26)+(d+16))/26)*26)+(f+11))/26)*26)+(h+16))*26)+(i+6))/26)/26)/26)%26-16==m){
                                if((((((((((((((((((((a+15)*26)+(b+10))*26)+(c+2))*26)+(d+16))/26)*26)+(f+11))/26)*26)+(h+16))*26)+(i+6))/26)/26)/26)/26)%26-14==n){
                                    z=(((((((((((((((((((a+15)*26)+(b+10))*26)+(c+2))*26)+(d+16))/26)*26)+(f+11))/26)*26)+(h+16))*26)+(i+6))/26)/26)/26)/26)/26;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(z == 0){
            System.out.print("FOUND ");
            System.out.print(a);
            System.out.print(b);
            System.out.print(c);
            System.out.print(d);
            System.out.print(e);
            System.out.print(f);
            System.out.print(g);
            System.out.print(h);
            System.out.print(i);
            System.out.print(j);
            System.out.print(k);
            System.out.print(l);
            System.out.print(m);
            System.out.print(n);
            System.out.println();
        }
    }



    private static void tester(){
        long a = 1, b= 1, c = 1, d = 1, e = 1, f = 1, g = 1, h = 1, i = 1, j = 1, k=1, l =1, m=1;
        long num = ((((((((((((((((((a+15)*26)+(b+10))*26)+(c+2))*26)+(d+16))/26)*26)+(f+11))/26)*26)+(h+16))*26)+(i+6))/26)*26)+(j+15))/26/26/26/26;
        long nun =((int)(((((((int)((((((((((a+15)*26)+(b+10))*26)+(c+2))*26)+(d+16))/26)*26)+(f+12))/26)*26)+(g+5))*26)+(h+16))*26)+(i+6))/26/26/26/26/26;
        System.out.println(num + " " + nun);
    }

    private static void matchCriteria(){
        ArrayList<Integer> aCopy = new ArrayList<>();
        ArrayList<Integer> bCopy = new ArrayList<>();
        ArrayList<Integer> cCopy = new ArrayList<>();
        ArrayList<Integer> dCopy = new ArrayList<>();
        ArrayList<Integer> eCopy = new ArrayList<>();
        ArrayList<Integer> fCopy = new ArrayList<>();
        ArrayList<Integer> gCopy = new ArrayList<>();
        ArrayList<Integer> hCopy = new ArrayList<>();
        ArrayList<Integer> iCopy = new ArrayList<>();
        ArrayList<Integer> jCopy = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();
        for(var ai : a){
            System.out.println(ai);
            for(var bi : b){
                for(var ci : c){
                    for(var di : d){
                        for(var ei : e){
                            for(var fi : f){
                                for(var gi : g) {
                                    for (var hi : h) {
                                        for (var ii : i) {
                                            for (var ji : j) {
                                                int num = (((((((((((((((ai+15)*26)+(bi+10))*26)+(ci+2))*26)+(di+16))/26)*26)+(fi+11))/26)*26)+(hi+16))*26)+(ii+6))%26-14;
                                                if (num == ji) {
                                                    if(!nums.contains(num)) nums.add(num);
                                                    if (!aCopy.contains(ai)) aCopy.add(ai);
                                                    if (!bCopy.contains(bi)) bCopy.add(bi);
                                                    if (!cCopy.contains(ci)) cCopy.add(ci);
                                                    if (!dCopy.contains(di)) dCopy.add(di);
                                                    if (!eCopy.contains(ei)) eCopy.add(ei);
                                                    if (!fCopy.contains(fi)) fCopy.add(fi);
                                                    if (!gCopy.contains(gi)) gCopy.add(gi);
                                                    if (!hCopy.contains(hi)) hCopy.add(hi);
                                                    if (!iCopy.contains(ii)) iCopy.add(ii);
                                                    if (!jCopy.contains(ji)) jCopy.add(ji);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(aCopy);
        System.out.println(bCopy);
        System.out.println(cCopy);
        System.out.println(dCopy);
        System.out.println(eCopy);
        System.out.println(fCopy);
        System.out.println(gCopy);
        System.out.println(hCopy);
        System.out.println(iCopy);
        System.out.println(jCopy);
        System.out.println(nums);
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
