package com.calculator.invest;

import java.util.Scanner;

public class ProfitMode extends Common {
    static ManageLosses manageLosses = new ManageLosses();
    static Scanner input = new Scanner(System.in);
    static String choice;

    public static void menu(){
        separateLine();
        System.out.println("**********PROFIT MODE**********\nManage your losses using the profit mode.");
        manageLossesModesMenu();
        enterChoiceDisplay();
        inputOutput();
        choice = input.nextLine();
        resetOutput();
        switch (choice){
            case "1":
                manageLosses.checkWithAll("profitMode");
                break;
            case "2":
                manageLosses.checkInvestAmount("profitMode");
                break;
            case "3":
                manageLosses.checkTrials("profitMode");
                break;
            case "4":
                manageLosses.menu();
                break;
            default:
                errorOutput();
                System.out.println("Invalid choice! Valid choices are 1, 2, 3, 4");
                resetOutput();
                menu();
                break;
        }
    }
}
