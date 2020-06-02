package com.calculator.invest;

import java.util.Scanner;

public class Invest_calculator{
    static Scanner input = new Scanner(System.in);
    static String choice;
    static Common common = new Common();
    static ManageLosses manageLosses = new ManageLosses();
    static ManageProfits manageProfits = new ManageProfits();

    public static void main(String [] args){
        welcomeMenu();
    }

    public static void welcome(){
        common.separateLine();
        System.out.println("**********WELCOME TO INVEST CALCULATOR**********\n*****A program to manage your stakes in bets or binary trades*****");
    }

    public static void welcomeMenu(){
        welcome();
        common.separateLine();
        System.out.println("\t1.Manage profits.\n\t2.Manage Losses\n\t3.How it works\n\t4.Exit");
        common.enterChoiceDisplay();
        common.inputOutput();
        choice = input.nextLine();
        common.resetOutput();
        switch(choice){
            case "1":
                manageProfits.menu();
                break;
            case "2":
                manageLosses.menu();
                break;
            case "3":
                howItWorks();
                break;
            case "4":
                System.out.println("exiting the program....\nBye...");
                System.exit(0);
                break;
            default:
//                System.out.println("\033[31;1mInvalid choice! Valid choices are 1, 2, 3, 4\033[0m");
                common.errorOutput();
                System.out.println("Invalid choice! Valid choices are 1, 2, 3, 4");
                common.resetOutput();
                welcomeMenu();
                break;
        }
    }

    public static void howItWorks(){
        System.out.println("Wort In Progress");
        welcomeMenu();
    }
}



