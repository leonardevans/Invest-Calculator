package com.calculator.invest;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageProfits extends Common {
    static Scanner input = new Scanner(System.in);
    static String choice;
    static Invest_calculator mainPage = new Invest_calculator();
    
    public static void menu(){
        separateLine();
        System.out.println("**********PROFIT MANAGEMENT**********");
        System.out.println("What do you want to calculate?");
        System.out.println("\t1.Profit made.\n\t2.Period of time needed.\n\t3.Amount to stake\n\t4.Best Odd.\n\t5.Interval\n\t6.Back");
        enterChoiceDisplay();
        inputOutput();
        choice = input.nextLine();
        resetOutput();
        switch(choice){
            case "1":
                profitToMake();
                break;
            case "2":
                timeNeeded();
                break;
            case "3":
                amountToStake();
                break;
            case "4":
                bestOdd();
                break;
            case "5":
                interval();
                break;
            case "6":
                mainPage.welcomeMenu();
                break;
            default:
                errorOutput();
                System.out.println("Invalid choice! Valid choices are 1, 2, 3, 4, 5, 6");
                resetOutput();
                menu();
                break;
        }
    }
    
    public static void profitToMake(){
        System.out.println("*********CALCULATE PROFIT MADE*********");
        double stake = getStakeInput();
        double odd = getOddInput();
        int interval = 0;
        int totalTime = 0;
        boolean badIntervalTotalTime = true;
        while(badIntervalTotalTime){
            interval = getTimeInput("interval");
            totalTime = getTimeInput("total time");

            if(totalTime > interval){
                badIntervalTotalTime = false;
            }
            else{
                System.out.println("Invalid input! Total time should be greater than the interval.");
            }
        }
        input.nextLine();
        //trials = total time / interval
        int trials = (int) Math.ceil(totalTime / interval);
        //profit made = trials * stake * (odd -1)
        double profitMade = trials * (stake * (odd - 1));
        display(roundToTwo(stake),roundToTwo( odd), timeToString(interval), timeToString(totalTime),roundToTwo( profitMade), trials, "profit");
    }

    public static void display(double stake, double odd, String interval, String totalTime, double profit, int trials, String lookingFor){
        //Line to display section and input section
        separateLine();
        displayOutput();
        System.out.println("RESULT:");
        if(lookingFor == "profit"){
            System.out.println("Stake: " + stake + "\nOdd: " + odd + "\nInterval: " + interval + "\nTotal time: " + totalTime + "\nTrials: " + trials );
            System.out.println("Profit made will be: "+ profit);
        }
        else if(lookingFor == "stake"){
            System.out.println("Odd: " + odd + "\nInterval: " + interval + "\nTotal time: " + totalTime + "\nTrials: " + trials + "\nProfit: " + profit);
            System.out.println("Stake will be: "+ stake);
        }
        else if(lookingFor == "odd"){
            System.out.println("Stake: " + stake + "\nInterval: " + interval + "\nTotal time: " + totalTime + "\nTrials: " + trials + "\nProfit: " + profit );
            System.out.println("Odd will be: "+ odd);
        }
        else if(lookingFor == "interval"){
            System.out.println("Stake: " + stake + "\nOdd: " + odd + "\nTotal time: " + totalTime + "\nTrials: " + trials + "\nProfit: " + profit );
            System.out.println("Interval will be: "+ interval);
        }
        else if(lookingFor == "totalTime"){
            System.out.println("Stake: " + stake + "\nOdd: " + odd + "\nInterval: " + interval + "\nTrials: " + trials + "\nProfit: " + profit );
            System.out.println("Total time will be: "+ totalTime);
        }
        resetOutput();
        //show the menu
        menu();
    }

    public static int getTimeInput(String value) {
        separateLine();
        int time = 0;
        boolean badTime = true;
        System.out.println("Enter the " + value + ":");
        while (badTime){
            int hours = getHrMinSec("hours") * (60 * 60);
            int minutes = getHrMinSec("minutes") * 60;
            int seconds = getHrMinSec("seconds");
            time = hours + minutes + seconds;
            if(time > 0){
                badTime = false;
            }else{
                errorOutput();
                System.out.println("Invalid input! " + value + "should be greater than 0");
                resetOutput();
            }
        }
        return  time;
    }

    public static String timeToString(int value){
        String time = "";
        int seconds;
        int minutes;
        int hours;
        if(value >= 3600){
            hours = (int) Math.floor(value/3600);
            int remainingSeconds = value % 3600;
            if(remainingSeconds>0){
                minutes = (int)Math.floor(remainingSeconds/60);
                if((remainingSeconds % 60)>0){
                    seconds = remainingSeconds % 60;
                    time = hours + " hours " + minutes + " minutes " + seconds + " seconds";
                }else{
                    time = hours + " hours " + minutes + " minutes ";
                }
            }else{
                time = hours + " hours ";
            }
        }
        else if(value >= 60 ){
            minutes = (int)Math.floor(value/60);
            if((value%60)>0){
                seconds = value % 60;
                time = minutes + " minutes " + seconds + " seconds";
            }
            else{
                time = minutes + " minutes ";
            }
        }
        else{
            time = value + " seconds";
        }
        return time;
    }

    public static  int getHrMinSec(String value){
        int time = 0;
        boolean badInput = true;
        while(badInput){
            try{
                System.out.print("\tEnter " + value + ":");
                inputOutput();
                time = input.nextInt();
                resetOutput();
                if(time >= 0 ) {
                    badInput = false;
                }
                else{
                    errorOutput();
                    System.out.println("Invalid input! " + value + " should not be less than 0");
                    resetOutput();
                }
            }
            catch (InputMismatchException e){
                errorOutput();
                System.out.println("Invalid input! " + value + " should be a number");
                resetOutput();
                input.nextLine();
            }
        }
        return time;
    }

    public static void timeNeeded(){
        separateLine();
        System.out.println("*********CALCULATE PERIOD OF TIME NEEDED*********");
        double stake = 0.0;
        double odd = getOddInput();
        int interval = getTimeInput("interval");
        double profit = 0.0;
        boolean badStakeProfit = true;
        while(badStakeProfit){
            stake = getStakeInput();
            profit = getProfitInput();
            if(profit > stake){
                badStakeProfit = false;
            }else{
                errorOutput();
                System.out.println("Invalid input! Profit to make should be greater than stake");
                resetOutput();
            }
        }
        input.nextLine();
        //trials = total profit / profit per trial
        //profit per trial = stake * (odd - 1)
        double profitPerTrial = stake * (odd - 1);
        int trials =  (int)Math.ceil(profit / profitPerTrial);
        //total time  = trials * interval
        int totalTime = trials * interval;
        display(roundToTwo(stake),roundToTwo( odd), timeToString(interval), timeToString(totalTime),roundToTwo( profit), trials, "totalTime");
    }

    public  static  double getProfitInput(){
        double profit = 0.0;
        boolean badProfit = true;
        while(badProfit){
            separateLine();
            try{
                System.out.print("Enter the profit to make:");
                inputOutput();
                profit = input.nextDouble();
                resetOutput();
                if (profit>0){
                    badProfit = false;
                }else{
                    errorOutput();
                    System.out.println("Invalid input! Profit should be greater than 0");
                    resetOutput();
                }
            }
            catch (InputMismatchException e){
                errorOutput();
                System.out.println("Invalid input! profit  should be a number.");
                resetOutput();
                input.nextLine();
            }
        }
        return profit;
    }
    
    public static void amountToStake(){
        separateLine();
        System.out.println("*********CALCULATE AMOUNT TO STAKE*********");
        double odd = getOddInput();
        double profit = getProfitInput();
        int totalTime = 0;
        int interval = 0;
        boolean badIntervalTotalTime = true;
        while (badIntervalTotalTime){
            totalTime = getTimeInput("total time");
            interval = getTimeInput("interval");
            if(totalTime > interval){
                badIntervalTotalTime = false;
            }
            else{
                errorOutput();
                System.out.println("Invalid input! Total time should be greater than interval");
                resetOutput();
            }
        }
        input.nextLine();
        //trials = total time / interval
        int trials = (int) Math.ceil(totalTime / interval);
        //profit per trial = total profit / trials
        double profitPerTrial = profit / trials;
        //stake = profit per trial / odd
        double stake = Math.round((profitPerTrial /(odd-1))* 100)/100.0;
        display(roundToTwo(stake),roundToTwo( odd), timeToString(interval), timeToString(totalTime),roundToTwo( profit), trials, "stake");
    }

    public static void bestOdd(){
        separateLine();
        System.out.println("*********CALCULATE THE BEST ODD*********");
        double stake = 0.0;
        double profit = 0.0;
        int totalTime = 0;
        int interval = 0;
        boolean badIntervalTotalTime = true;
        boolean badStakeProfit = true;
        while(badStakeProfit){
            stake = getStakeInput();
            profit = getProfitInput();
            if(profit > stake){
                badStakeProfit = false;
            }
            else{
                errorOutput();
                System.out.println("Invalid input! Profit should be greater than stake.");
                resetOutput();
            }
        }
        input.nextLine();
        while(badIntervalTotalTime){
            interval = getTimeInput("interval");
            totalTime = getTimeInput("totalTime");
            if(totalTime > interval){
                badIntervalTotalTime= false;
            }
            else{
                errorOutput();
                System.out.println("Invalid input! Total time should be greater than interval.");
                resetOutput();
            }
        }
        input.nextLine();
        //trials = total time / interval
        int trials = (int) Math.ceil(totalTime / interval);
        //profit per trial = total profit / trials
        double profitPerTrial = profit / trials;
        //odd = profit per trial / stake
        double odd =1 +  roundToTwo((profitPerTrial / stake));
        //display the findings
        display(roundToTwo(stake),roundToTwo( odd), timeToString(interval), timeToString(totalTime),roundToTwo( profit), trials, "odd");
    }

    public static void interval(){
        separateLine();
        System.out.println("*********CALCULATE THE BEST INTERVAL*********");
        double odd = getOddInput();
        double stake = 0.0;
        double profit = 0.0;
        boolean badStakeProfit = true;

        while (badStakeProfit){
            stake = getStakeInput();
            profit = getProfitInput();
            if(profit > stake){
                badStakeProfit = false;
            }
            else{
                errorOutput();
                System.out.println("Invalid input! Profit should be greater than stake.");
                resetOutput();
            }
        }


        int totalTime = getTimeInput("total time");

        input.nextLine();


        //profit per trial = stake * (odd - 1)
        double profitPerTrial = stake * (odd - 1);

        //trials = total profit / profit per trial
        int trials = (int) Math.ceil(profit / profitPerTrial);

        //interval = total time/ trials
        int interval = (int) Math.ceil( totalTime / trials );

        display(roundToTwo(stake),roundToTwo( odd), timeToString(interval), timeToString(totalTime),roundToTwo( profit), trials, "interval");

    }
}