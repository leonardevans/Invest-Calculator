package com.calculator.invest;

import java.util.Scanner;

public class RecoveryMode  extends Common {
    static ManageLosses manageLosses = new ManageLosses();
    static Scanner input = new Scanner(System.in);
    static String choice;

    public static void menu(){
        separateLine();
        System.out.println("**********RECOVERY MODE**********\nManage your losses using the recovery mode.");
        manageLossesModesMenu();
        enterChoiceDisplay();
        inputOutput();
        choice = input.nextLine();
        resetOutput();
        switch (choice){
            case "1":
                manageLosses.checkWithAll("recoveryMode");
                break;
            case "2":
                manageLosses.checkInvestAmount("recoveryMode");
                break;
            case "3":
                manageLosses.checkTrials("recoveryMode");
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
