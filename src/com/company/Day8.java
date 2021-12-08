package com.company;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day8 {

    private static ArrayList<ArrayList<String>> input = new ArrayList<>();
    private static ArrayList<ArrayList<String>> output = new ArrayList<>();


    public static void Day8(){

        fileIO();

        int sum = 0;
        for(var out: output){
            for(var x: out){
//                System.out.println(x);
                if(x.length() == 2){
                    sum++;
                }
                if(x.length() == 4){
                    sum++;
                }
                if(x.length() == 3){
                    sum++;
                }
                if(x.length() == 7){
                    sum++;
                }
            }
        }


        System.out.println(sum);

        sum = 0;
        for(int k = 0; k < input.size(); k++) {

            ArrayList<String> temp = input.get(k);
            ArrayList<Character> a = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g'));
            ArrayList<Character> b = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g'));
            ArrayList<Character> c = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g'));
            ArrayList<Character> d = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g'));
            ArrayList<Character> e = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g'));
            ArrayList<Character> f = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g'));
            ArrayList<Character> g = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g'));

            for (var num : temp) {
                if (num.length() == 2) { //has to be 1
                    a = filterArr(a, opposite(num));
                    b = filterArr(b, opposite(num));
                    c = filterArr(c, num);
                    d = filterArr(d, opposite(num));
                    e = filterArr(e, opposite(num));
                    f = filterArr(c, num);
                    g = filterArr(g, opposite(num));
                }
                if (num.length() == 4) { //has to be 4
                    a = filterArr(a, opposite(num));
                    b = filterArr(b, num);
                    c = filterArr(c, num);
                    d = filterArr(d, num);
                    e = filterArr(e, opposite(num));
                    f = filterArr(f, num);
                    g = filterArr(g, opposite(num));
                }
                if (num.length() == 3) { //has to be 7
                    a = filterArr(a, num);
                    b = filterArr(b, opposite(num));
                    c = filterArr(c, num);
                    d = filterArr(d, opposite(num));
                    e = filterArr(e, opposite(num));
                    f = filterArr(f, num);
                    g = filterArr(g, opposite(num));
                }
            }


            ArrayList<String> valids = new ArrayList<>();

            ArrayList<String> combos = new ArrayList<>();
            for (int i = 0; i < a.size(); i++) {
                for (int ii = 0; ii < b.size(); ii++) {
                    for (int iii = 0; iii < c.size(); iii++) {
                        for (int iv = 0; iv < d.size(); iv++) {
                            for (int v = 0; v < e.size(); v++) {
                                for (int vi = 0; vi < f.size(); vi++) {
                                    for (int vii = 0; vii < g.size(); vii++) {
                                        String t = "";
                                        boolean invalid = false;
                                        t += a.get(i);
                                        t += b.get(ii);
                                        t += c.get(iii);
                                        t += d.get(iv);
                                        t += e.get(v);
                                        t += f.get(vi);
                                        t += g.get(vii);

                                        if (checkForDuplicateCharacters(t)) {
                                            invalid = true;
                                        }

                                        for (int j = 0; j < temp.size() && !invalid; j++) {
                                            String num = temp.get(j);
                                            if (num.length() == 6) {//0,6,9
                                                if (num.indexOf(t.charAt(3)) == -1 ||
                                                        num.indexOf(t.charAt(2)) == -1 ||
                                                        num.indexOf(t.charAt(4)) == -1
                                                ) {
                                                } else {
                                                    //                                                System.out.println(t + " " + num + );
                                                    invalid = true;
                                                }
                                            }
                                            if (num.length() == 5) {//2,3,5
                                                if ((num.indexOf(t.charAt(1)) == -1 && num.indexOf(t.charAt(5)) == -1) ||
                                                        (num.indexOf(t.charAt(1)) == -1 && num.indexOf(t.charAt(4)) == -1) ||
                                                        (num.indexOf(t.charAt(2)) == -1 && num.indexOf(t.charAt(4)) == -1)
                                                ) {
                                                } else {
                                                    //                                                System.out.println(t + " " + num + );
                                                    invalid = true;
                                                }
                                            }
                                        }
                                        if (!invalid) {
                                            valids.add(t);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Integer num = Integer.parseInt(decode(valids.get(0), output.get(k)));
            System.out.println(valids + " " + num);
            sum += num;
        }
        System.out.println(sum);
    }

    private static String decode(String code, ArrayList<String> message){
        String num = "";
        for(var m: message){
            if(m.length() == 2){ //1
                num += "1";
            }
            if(m.length() == 3){
                num += "7";
            }
            if(m.length() == 4){ // 4
                num += "4";
            }
            if(m.length() == 5){ //2,3,5
                if(m.indexOf(code.charAt(1)) == -1 && m.indexOf(code.charAt(5)) == -1){
                    num += "2";
                }
                else if(m.indexOf(code.charAt(1)) == -1 && m.indexOf(code.charAt(4)) == -1){
                    num += "3";
                }else{
                    num += "5";
                }
            }
            if(m.length() == 6){//0,6,9
                if(m.indexOf(code.charAt(3)) == -1){
                    num += "0";
                }else if(m.indexOf(code.charAt(2)) == -1){
                    num += "6";
                }else{
                    num += "9";
                }
            }
            if(m.length() == 7){
                num += "8";
            }
        }
        return num;
    }

    private static boolean checkForDuplicateCharacters(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i)))
                return true;
            map.put(s.charAt(i), 0);
        }
        return false;
    }

    private static String opposite(String s){
        String op = "";
        ArrayList<Character> chars = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g'));
        ArrayList<Character> chars2 = new ArrayList<>();
        for(char x: s.toCharArray()){
            chars2.add(x);
        }
        for(var x: chars){
            if(!chars2.contains(x))
                op += x;
        }
        return op;
    }

    private static ArrayList<Character> filterArr(ArrayList<Character> arr, String filter){
        ArrayList<Character> newArr = new ArrayList<>();
        for(char c : filter.toCharArray()){
            if(arr.contains(c)){
                newArr.add(c);
            }
        }

        return newArr;
    }

    private static void fileIO(){
        File inputFile = new File("src/com/company/Day8Input");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] arr = data.split("\\|");

                String[] arrIn = arr[0].split(" ");
                String[] arrOut = arr[1].split(" ");
                ArrayList<String> lI = new ArrayList<>();
                ArrayList<String> lO = new ArrayList<>();
                for(var s: arrIn){
                    lI.add(s);
                }
                for(var s: arrOut){
                    lO.add(s);
                }
                input.add(lI);
                output.add(lO);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
