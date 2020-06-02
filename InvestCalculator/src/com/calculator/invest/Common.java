package com.calculator.invest;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Common{
    static Scanner input = new Scanner(System.in);

    //ANSI Colors
    public static final String ANSI_RESET  = "\u001B[0m";

    public static final String ANSI_BLACK  = "\u001B[30m";
    public static final String ANSI_RED    = "\u001B[31m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE   = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_WHITE  = "\u001B[37m";


    public static void separateLine(){ System.out.println(ANSI_PURPLE + "________________________________________________________________" + ANSI_RESET);}
    
    public static void enterChoiceDisplay(){
        System.out.print("Enter choice: ");
    }

    public static double getStakeInput(){
        double stake = 0.0;
        boolean badStake = true;
        while(badStake){
            separateLine();
            try{
                System.out.print("Enter the stake amount:");
                inputOutput();
                stake = input.nextDouble();
                resetOutput();
                if (stake>0){
                    badStake = false;
                }else{
                    errorOutput();
                    System.out.println("Invalid input! Stake should be greater than 0");
                    resetOutput();
                }
            }
            catch (InputMismatchException e){
                errorOutput();
                System.out.println("Invalid input! Stake should be a number.");
                resetOutput();
                input.nextLine();
            }
        }
        return stake;
    }

    public static double getOddInput(){
        double odd = 0.0;
        boolean badOdd = true;
        while(badOdd){
            separateLine();
            try{
                System.out.print("Enter the odd:");
                inputOutput();
                odd = input.nextDouble();
                resetOutput();
                if (odd>1){
                    badOdd = false;
                }else{
                    errorOutput();
                    System.out.println("Invalid input! Stake should be greater than 1");
                    resetOutput();
                }
            }
            catch (InputMismatchException e){
                errorOutput();
                System.out.println("Invalid input! Odd  should be a number.");
                resetOutput();
                input.nextLine();
            }
        }
        return odd;
    }

    public static int getTrialsInput(){
        boolean badTrials = true;
        int trials = 0;
        while(badTrials){
            separateLine();
            try{
                System.out.print("Enter the number of trials:");
                inputOutput();
                trials = input.nextInt();
                resetOutput();
                if (trials>0){
                    badTrials = false;
                }else{
                    errorOutput();
                    System.out.println("Invalid input! Trials should be at least 1");
                    resetOutput();
                }
            }
            catch (InputMismatchException e){
                errorOutput();
                System.out.println("Invalid input! Trials should be a whole number.");
                resetOutput();
                input.nextLine();
            }
        }
        input.nextLine();
        return trials;
    }

    public static double getInvestInput(){
        double investAmount = 0.0;
        boolean badInvestAmount = true;
        while(badInvestAmount){
            separateLine();
            try{
                System.out.print("Enter the amount to invest:");
                inputOutput();
                investAmount = input.nextDouble();
                resetOutput();
                if (investAmount>0){
                        badInvestAmount = false;
                }else{
                    errorOutput();
                    System.out.println("Invalid input! Amount to invest should be more than 0");
                    resetOutput();
                }
            }
            catch (InputMismatchException e){
                errorOutput();
                System.out.println("Invalid input! Amount to invest should be a number.");
                resetOutput();
                input.nextLine();
            }
        }
        input.nextLine();
        return investAmount;
    }

    public static boolean correctStakeInvest(double stake, double invest){
        if(invest > stake){
            return true;
        }else{
            errorOutput();
            System.out.println("Amount to invest should be greater than the stake amount");
            resetOutput();
            return false;
        }
    }

    public static void manageLossesModesMenu(){
        System.out.println("Manage losses using odd, stake amount, trials and invest amount");
        System.out.println("\t1.Manage with all\n\t2.Check amount to invest\n\t3.Check number of trials\n\t4.Back");

    }

    public  static double getWInValue(double odd, double profit){
        return (odd * profit) / (odd - 1);
    }

    public static  double getStakeValue(double odd, double profit){
        return profit/(odd - 1);
    }

    public static double roundToTwo(double value){
        return Math.round(value * 100) / 100.0;
    }

    public static void errorOutput(){
        System.out.print(ANSI_RED);
    }

    public static void resetOutput(){
        System.out.print(ANSI_RESET);
    }

    public static void inputOutput(){
        System.out.print(ANSI_YELLOW);
    }

    public static void displayOutput(){
        System.out.print(ANSI_BLUE);
    }

}