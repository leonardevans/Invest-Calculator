package com.calculator.invest;

import java.util.Scanner;

public class StrictMode extends Common {
    static ManageLosses manageLosses = new ManageLosses();
    static Scanner input = new Scanner(System.in);
    static String choice;

    public static void menu(){
        separateLine();
        System.out.println("**********STRICT MODE**********\nManage your losses using the strict mode.");
        manageLossesModesMenu();
        enterChoiceDisplay();
        inputOutput();
        choice = input.nextLine();
        resetOutput();
        switch (choice){
            case "1":
                manageLosses.checkWithAll("strictMode");
                break;
            case "2":
                manageLosses.checkInvestAmount("strictMode");
                break;
            case "3":
                manageLosses.checkTrials("strictMode");
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
