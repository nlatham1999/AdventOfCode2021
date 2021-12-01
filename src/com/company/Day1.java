package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Day1 {

    public static void Day1(){
        System.out.println("Testing 123");

        ArrayList<Integer> arr = loadList();
        System.out.println(arr);
    }

    public static ArrayList<Integer> loadList(){
        ArrayList<Integer> newList = new ArrayList<Integer>(Arrays.asList(1, 3, 5));

        return newList;
    }
}
