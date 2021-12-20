package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

class SnailPair {
    public int value = 0;
    public boolean regularValue = true;
    public boolean head = false;
    public SnailPair left;
    public SnailPair right;
    public SnailPair parent = null;
    public boolean isLeft = true;
    public int level = 0;

    SnailPair(int value){
        this.value = value;
    }
}

public class Day18 {



    private static SnailPair snailPair = new SnailPair(0);
    private static int counter = 0;
    private static boolean stopTraverse = false;
    private static int level = 0;

    private static ArrayList<ArrayList<String>> numbers = new ArrayList<>();


    public static void Day18(){

        fileIO();

//        partOne();
        partTwo();
    }

    private static void partTwo(){
        int max = 0;
        for(int i = 0; i < numbers.size(); i++){
            for(int j = 0; j < numbers.size(); j++){
                if(i != j){
                    counter = 1;
                    var left = constructPairs(numbers.get(i));
                    counter = 1;
                    var right = constructPairs(numbers.get(j));
                    snailPair = new SnailPair(0);
                    snailPair.head = true;
                    snailPair.regularValue = false;
                    snailPair.left = left;
                    snailPair.right = right;
                    snailPair.left.parent = snailPair;
                    snailPair.right.parent = snailPair;
                    snailPair.right.head = false;
                    snailPair.left.head = false;
                    snailPair.right.isLeft = false;
                    simplify();
                    int score = calculateMagnitude(snailPair);
                    if(score > max){
                        max = score;
                        System.out.println(max);
                    }
                }
            }
        }
    }

    private static void partOne(){

        boolean notStarted = true;
        for(var subList : numbers){
            counter = 1;
            var snailPairTemp = constructPairs(subList);
            if(notStarted){
                notStarted = false;
                snailPair = snailPairTemp;
                snailPair.head = true;
            }else{
                var temp = snailPair;
                snailPair = new SnailPair(0);
                snailPair.left = temp;
                snailPair.right = snailPairTemp;
                snailPair.right.isLeft = false;
                snailPair.regularValue = false;
                snailPair.left.parent = snailPair;
                snailPair.right.parent = snailPair;
                snailPair.right.head = false;
                snailPair.left.head = false;
            }
            setLevels(snailPair, 1);
            simplify();
            printPairs(snailPair);
            System.out.println();
        }
//        simplify();
        System.out.println(calculateMagnitude(snailPair));
    }

    private static int calculateMagnitude(SnailPair snail){
        if(snail.regularValue){
            return snail.value;
        }else{
            return (3*calculateMagnitude(snail.left))+(2*calculateMagnitude(snail.right));
        }
    }

    private static void simplify(){
        stopTraverse = true;
        while(stopTraverse){
            stopTraverse = false;
            setLeftRights(snailPair);
            traverseExplode(snailPair, 1);
            if(!stopTraverse){
                traverseSplit(snailPair);
            }
//            printPairs(snailPair);
//            System.out.println();
        }
    }

    private static void traverseSplit(SnailPair snail){
        if(snail.regularValue){
            if(snail.value > 9){
                split(snail);
                stopTraverse = true;
                return;
            }
        }else{
            traverseSplit(snail.left);
            if(!stopTraverse)
                traverseSplit(snail.right);
        }
        return;
    }

    private static void traverseExplode(SnailPair snail, int level){
        if(level > 4 && !snail.regularValue && snail.left.regularValue && snail.right.regularValue){
            explode(snail);
            stopTraverse = true;
            return;
        }
        if(snail.regularValue){
            return;
        }else{
            traverseExplode(snail.left, level+1);
            if(!stopTraverse)
                traverseExplode(snail.right, level+1);
        }
        return;
    }

    private static void split(SnailPair snail){
        snail.regularValue = false;
        int left = (int) Math.floor((double)(snail.value) / 2);
        int right = (int) Math.ceil((double)(snail.value) / 2);
        snail.left = new SnailPair(left);
        snail.right = new SnailPair(right);

        snail.left.parent = snail;
        snail.right.parent = snail;

        setLevels(snailPair, 0);
//        if(snail.level > 4){
//            explode(snail);
//        }
    }

    private static void explode(SnailPair snail){
        int left = snail.left.value;
        int right = snail.right.value;
        snail.regularValue = true;
        snail.value = 0;

        var snailTemp = goUpAndFindRight(snail);
        if(snailTemp != null){
            setRightMost(snailTemp.left, left);
        }

        var snailTemp2 = goUpAndFindLeft(snail);
        if(snailTemp2 != null){
            setLeftMost(snailTemp2.right, right);
        }
    }

    private static void setLeftMost(SnailPair snail, int right){
        if(snail.regularValue){
            snail.value += right;
            if(snail.value > 9){
//                split(snail);
            }
        }else{
            setLeftMost(snail.left, right);
        }
    }

    private static SnailPair goUpAndFindLeft(SnailPair snail){
        if(snail.parent == null){
            return null;
        }
        if(snail == null){
            return null;
        }
        if(!snail.isLeft){
            if(snail.parent.head)
                return null;
            return goUpAndFindLeft(snail.parent);
        }else{
            return snail.parent;
        }
    }

    private static void setRightMost(SnailPair snail, int left){
        if(snail.regularValue){
            snail.value += left;
            if(snail.value > 9){
//                split(snail);
            }
        }else{
            setRightMost(snail.right, left);
        }
    }

    private static SnailPair goUpAndFindRight(SnailPair snail){
        if(snail.parent == null){
            return null;
        }
        if(snail == null){
            return null;
        }
        if(snail.isLeft){
            if(snail.parent.head)
                return null;
            return goUpAndFindRight(snail.parent);
        }else{
            return snail.parent;
        }
    }


    private static void fileIO(){
        File inputFile = new File("src/com/company/Day18Input");
        try {
            Scanner reader = new Scanner(inputFile);
            ArrayList<String> list = new ArrayList<>();
            boolean notStarted = true;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                ArrayList<String> subList = new ArrayList<>();
                for(int i = 0; i < data.length(); i++){
                    if(data.charAt(i) != ','){
                        subList.add(String.valueOf(data.charAt(i)));
                    }

                }
                numbers.add(subList);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printPairs(SnailPair snail){
        if(snail.regularValue){
            System.out.print(snail.value);
        }else{
            System.out.print("[");
            printPairs(snail.left);
            System.out.print(",");
            printPairs(snail.right);
            System.out.print("]");
        }
    }

    private static SnailPair constructPairs(ArrayList<String> arr){
        SnailPair left;
        SnailPair right;
        SnailPair p = new SnailPair(0);
        if(arr.get(counter).equals("[")){
            counter++;
            left = constructPairs(arr);
            left.regularValue = false;
        }else{
            left = new SnailPair(Integer.parseInt(arr.get(counter)));
            counter++;
        }
        if(arr.get(counter).equals("[")){
            counter++;
            right = constructPairs(arr);
            right.regularValue = false;
        }else{
            right = new SnailPair(Integer.parseInt(arr.get(counter)));
            counter++;
        }
        p.left = left;
        p.right = right;
        p.left.parent = p;
        p.right.parent = p;
        p.right.isLeft = false;
        counter++;
        p.regularValue = false;
        return p;
    }

    private static void setLevels(SnailPair snail, int level){
        snail.level = level;
        if(!snail.regularValue){
            setLevels(snail.left, level+1);
            setLevels(snail.right, level+1);
        }
    }

    private static void setLeftRights(SnailPair snail){
        if(snail.regularValue)
            return;
        snail.left.isLeft = true;
        snail.right.isLeft = false;
        setLeftRights(snail.left);
        setLeftRights(snail.right);
    }


}
