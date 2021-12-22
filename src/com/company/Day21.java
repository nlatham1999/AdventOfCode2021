package com.company;

public class Day21 {

    static int playerOnePos = 7;
    static int playerTwoPos = 8;
    static int playerOneScore = 0;
    static int playerTwoScore = 0;
    static int dice = -1;
    static int rolls = 0;

    static long numP1 = 0;
    static long numP2 = 0;

    public static void Day21(){

//        partOne();
        partTwo();
    }

    private static void partTwo(){

        singleRow(3, true, 0, 0, 8, 9, 1);
        singleRow(4, true, 0, 0, 8, 9, 3);
        singleRow(6, true, 0, 0, 8, 9, 7);
        singleRow(7, true, 0, 0, 8, 9, 6);
        singleRow(8, true, 0, 0, 8, 9, 3);
        singleRow(5, true, 0, 0, 8, 9, 6);
        singleRow(9, true, 0, 0, 8, 9, 1);

        System.out.println(numP1);
        System.out.println(numP2);
    }

    private static void singleRow(int amount, boolean p1turn, int p1score, int p2score, int p1pos, int p2pos, long factor){
        if(p1turn){
            p1pos = (amount + p1pos);
            if(p1pos > 10){
                p1pos -= 10;
            }
            p1score += p1pos;
        }else{
            p2pos = (amount + p2pos);
            if(p2pos > 10){
                p2pos -= 10;
            }
            p2score += p2pos;
        }

        if(p1score >= 21){
            numP1 += factor;
//            System.out.println(numP1 + " " + numP2);
            return;
        }
        if(p2score >= 21){
            numP2 += factor;
//            System.out.println(numP1 + " " + numP2);
            return;
        }

        singleRow(3, !p1turn, p1score, p2score, p1pos, p2pos, factor*1);
        singleRow(4, !p1turn, p1score, p2score, p1pos, p2pos, factor*3);
        singleRow(5, !p1turn, p1score, p2score, p1pos, p2pos, factor*6);
        singleRow(6, !p1turn, p1score, p2score, p1pos, p2pos, factor*7);
        singleRow(7, !p1turn, p1score, p2score, p1pos, p2pos, factor*6);
        singleRow(8, !p1turn, p1score, p2score, p1pos, p2pos, factor*3);
        singleRow(9, !p1turn, p1score, p2score, p1pos, p2pos, factor*1);
    }

    private static void partOne() {
        while (playerOneScore < 1000 && playerTwoScore < 1000) {
            for(int i = 0; i < 3; i++){
                rollDice();
                playerOnePos += dice+1;
            }
            playerOnePos = playerOnePos % 10;
            playerOneScore += playerOnePos + 1;

            if(playerOneScore < 1000){
                for(int i = 0; i < 3; i++){
                    rollDice();
                    playerTwoPos += dice+1;
                }
                playerTwoPos = playerTwoPos % 10;
                playerTwoScore += playerTwoPos + 1;
            }
        }

        int finalscore = 0;
        if(playerOneScore < playerTwoScore){
            finalscore = playerOneScore * rolls;
        }else{
            finalscore = playerTwoScore * rolls;
        }

        System.out.println(playerOneScore + " " + playerTwoScore + " " + rolls);
        System.out.println(finalscore);
    }

    private static void rollDice(){
        dice = (dice + 1) % 100;
        rolls++;
    }

}
