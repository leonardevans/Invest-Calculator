package com.calculator.invest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageLosses extends Common {
    static Scanner input = new Scanner(System.in);
    static String choice;
    static ProfitMode profitMode = new ProfitMode();
    static RecoveryMode recoveryMode = new RecoveryMode();
    static StrictMode strictMode = new StrictMode();
    static Invest_calculator mainPage = new Invest_calculator();
    static List<Double> stakes = new ArrayList<>();
    static List<Double> totalLosses = new ArrayList<>();
    static List<Double> wins = new ArrayList<>();
    static List<Double> profits = new ArrayList<>();
    static List<Double> totalProfits = new ArrayList<>();

    public static void menu(){
        separateLine();
        System.out.println("**********LOSS MANAGEMENT**********");
        System.out.println("Manage your losses using:");
        System.out.println("\t1.The recovery mode.\n\t2.The profit mode.\n\t3.The strict mode.\n\t4.Back");
        enterChoiceDisplay();
        inputOutput();
        choice = input.nextLine();
        resetOutput();
        switch(choice){
            case "1":
                //recovery  mode
                RecoveryMode recoveryMode = new RecoveryMode();
                recoveryMode.menu();
                break;
            case "2":
                //profit mode
                ProfitMode profitMode = new ProfitMode();
                profitMode.menu();
                break;
            case "3":
                //strict mode
                strictMode.menu();
                break;
            case "4":
                mainPage.welcomeMenu();
                break;
            default:
                errorOutput();
                System.out.println("Invalid choice! Valid choices are 1, 2, 3, 4");
                resetOutput();
                menu();
                break;
        }
    }

    public static void checkWithAll(String mode){
        double stake = 0.0;
        double odd = getOddInput();
        double invest = 0.0;
        int trials = getTrialsInput();
        boolean badInvestStake = true;
        while (badInvestStake){
            stake = getStakeInput();
            invest = getInvestInput();
            if(correctStakeInvest(stake, invest)){
                badInvestStake = false;
            }
        }
        calculator(stake, odd, invest, trials, mode);
    }

    public static void checkInvestAmount(String mode){
        double stake = getStakeInput();
        double odd = getOddInput();
        int trials = getTrialsInput();
        calculator(stake, odd, 0.0, trials, mode);
    }

    public static void checkTrials(String mode){
        double stake = 0.0;
        double odd = getOddInput();
        double invest = 0.0;
        boolean badInvestStake = true;
        while (badInvestStake){
            stake = getStakeInput();
            invest = getInvestInput();
            if(correctStakeInvest(stake, invest)){
                badInvestStake = false;
            }
        }
        calculator(stake, odd, invest, 0, mode);

    }

    public static void clearLists(){
        totalLosses.clear();
        totalProfits.clear();
        profits.clear();
        wins.clear();
        stakes.clear();
    }

    public static void calculator( double stake, double odd, double invest, int trials, String mode){
        boolean stopLoss = false;
        double totalLoss = 0.0;
        double profit = 0.0;
        double totalProfit = 0.0;
        double win = 0.0;
        int demoTrials = 0;
        String lookingFor = "";
        double mainProfit = stake * (odd-1);

        while (!stopLoss){
            demoTrials++;
            if(demoTrials > 1){
                switch (mode){
                    case "recoveryMode":
                        profit = totalLoss;
                        break;
                    case "profitMode":
                        profit = totalLoss + mainProfit;
                        break;
                    case "strictMode":
                        profit = totalLoss + (mainProfit*demoTrials);
                        break;
                    default:
                        errorOutput();
                        System.out.println("Error! No such mode: " + mode);
                        resetOutput();
                        clearLists();
                        menu();
                        break;
                }
                stake = getStakeValue(odd, profit);
            }else{
                profit = stake * (odd - 1);
            }
            totalProfit = profit - totalLoss;
            win = getWInValue(odd, profit);
            totalLoss += stake;
            stakes.add(roundToTwo(stake));
            wins.add(roundToTwo(win));
            profits.add(roundToTwo(profit));
            totalProfits.add(roundToTwo(totalProfit));
            totalLosses.add(roundToTwo(totalLoss));
            if(trials !=0 && invest == 0.0){
                if(trials == demoTrials){
                    stopLoss = true;
                }
            }else if (invest != 0.0 && trials == 0){
                double nextProfit = 0.0;
                switch (mode){
                    case "recoveryMode":
                        nextProfit = totalLoss;
                        break;
                    case "profitMode":
                        nextProfit = totalLoss + mainProfit;
                        break;
                    case "strictMode":
                        nextProfit = totalLoss + (mainProfit*demoTrials);
                        break;
                    default:
                        errorOutput();
                        System.out.println("Error! No such mode: " + mode);
                        resetOutput();
                        clearLists();
                        menu();
                        break;
                }
                double nextStake = getStakeValue(odd, nextProfit);
                double nextTotalLoss = totalLoss + nextStake;
                if(nextTotalLoss > invest){
                    stopLoss = true;
                }
            }
            else if(trials != 0 && invest != 0.0){
                if(demoTrials == trials){

                    stopLoss = true;
                }
            }
        }
        if(trials == 0){
            lookingFor = "trials";
        }else if(invest == 0){
            lookingFor = "invest";
        }
        manageLossesDisplay( stakes,  odd,  wins,  profits,  totalProfits,  totalLosses, invest, lookingFor );
        clearLists();
        switch (mode){
            case "recoveryMode":
                recoveryMode.menu();
                break;
            case "profitMode":
                profitMode.menu();
                break;
            case "strictMode":
                strictMode.menu();
                break;
            default:
                errorOutput();
                System.out.println("Error! No such mode: " + mode);
                resetOutput();
                menu();
                break;
        }
    }


    public static void manageLossesDisplay(List<Double> stakes, double odd, List<Double> wins, List<Double> profits, List<Double> totalProfits, List<Double> totalLosses, double invest, String lookingFor ){
        int total = stakes.size();
        separateLine();
        displayOutput();
        System.out.println("RESULT:");
        System.out.println("Trial\tStake\tOdd\tWin\tProfit\tTotal Profit\tTotal Loss");;
        for ( int i=0; i<total; i++){
            System.out.println((i+1) + "\t" + stakes.get(i) + "\t" + odd + "\t" + wins.get(i) + "\t" + profits.get(i) + "\t" + totalProfits.get(i) + "\t\t" + totalLosses.get(i));
        }
        if(lookingFor == "trials"){
            double amountLeft =roundToTwo( invest - totalLosses.get(total-1));
            if(amountLeft < 0){
                System.out.println("You will have made " +  amountLeft + " loss.");
            }
            else{
                System.out.println("You will have " + amountLeft + " remaining. You will not be able to recover the total amount you have lost if you stake the amount left.");
            }
        }
        else if (lookingFor == "invest"){
            System.out.println("You will need at least " + totalLosses.get(total-1) + " to invest using the strategy you chose.");
        }
        else if (lookingFor == ""){
            double amountLeft =roundToTwo( invest - totalLosses.get(total-1));
            if(amountLeft < 0){
                System.out.println("Oops! Looks like your strategy will lead to a loss. You will have made " + (amountLeft * -1) + " loss since in the last trial(" + total + ") the total loss would be " + totalLosses.get(total-1) + " which is greater than the amount you intend to invest(" +invest + ") by " + (amountLeft * -1));
            }
            else{
                System.out.println("You will have " + amountLeft + " remaining.");
            }
        }
        resetOutput();
    }

}