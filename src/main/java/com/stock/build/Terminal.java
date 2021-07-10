package com.stock.build;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Terminal {

    void run() throws Exception {
        File file = new File("/Users/jasonmoreau/Desktop/Consolidate/API Keys/rapidapi.txt");
        FileInputStream in = new FileInputStream(file);
        byte [] arr = new byte[50]; 	// must match size of key length
        in.read(arr);
        Scanner sc = new Scanner(System.in);
        String key = new String(arr);
        boolean start = true;
        int selection = 0;

        programTitle();

        while(start) {
            mainMenu();
            selection = sc.nextInt();
            sc.nextLine();				// clear scanner
            switch(selection) {
                case 1: quoteTitle();
                    Stocks.callQuote(key, sc);
                    break;
                case 2: newsTitle();
                    Stocks.callNews(key, sc);
                    break;
                case 3: economicTitle();
                    economicMenu(sc);
                    break;
                case 4: tradingTitle();		// as a placeholder
                    trade();
                    break;
                case 5: portfolioTitle();	// as a placeholder
                    portfolio();
                    break;
                case 6: System.out.println("\nGoodbye");
                    start = false;
                    break;
                default: break;

            }
        }

        sc.close();
        in.close();
    }

    void economicMenu(Scanner sc) throws Exception {
        int selection = 0;
        System.out.println("Menu\n");
        System.out.println("1. Recent US GDP Data\n");
        System.out.print("Selection: ");
        selection = sc.nextInt();
        switch(selection) {
            case 1: String dataType = "series/observations";
                String seriesId = "GDPC1";
                boolean recent = true;
                EconomicData.gdp(dataType, seriesId, recent);
                break;
        }

    }

    void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    void programTitle() {
        clearScreen();
        System.out.flush();
        System.out.println("#####################################################");
        System.out.println("#                                                   #");
        System.out.println("#                                                   #");
        System.out.println("#                   Stock Terminal                  #");
        System.out.println("#                                                   #");
        System.out.println("#                                                   #");
        System.out.println("#####################################################\n");
    }

    void mainMenu() {
        System.out.println("*** Main Menu ***\n ");
        System.out.println("1. Get Quotes");
        System.out.println("2. Latest News");
        System.out.println("3. Economic Data");
        System.out.println("4. Start Trading --> as part of a database");
        System.out.println("5. View Portfolio --> as part of a database");
        System.out.println("6. Quit\n");
        System.out.print("Selection: ");
    }

    void quoteTitle() {
        clearScreen();
        System.out.println("\n*** Stock Quotes *** \n");
    }

    void newsTitle() {
        clearScreen();
        System.out.println("\n*** Latest News *** \n");
    }

    void economicTitle() {
        clearScreen();
        System.out.println("\n*** Economic Data *** \n");
    }

    void tradingTitle() {
        clearScreen();
        System.out.println("\n*** Trading Dashboard *** ");

    }

    void portfolioTitle() {
        clearScreen();
        System.out.println("\n*** Portfolio *** ");
    }

    void trade() {
        System.out.println("Placeholder\n");
        /* Account creation / login screen
         * Menu options (buy/sell/trade, fund account, etc)
         * Stock predictions (ML)
         * Predictions based on stocks in your portfolio
         * Database updated
         */
    }

    void portfolio() {
        System.out.println("Placeholder\n");
        /* Login screen
         * Code to grab all current stock prices and and make calculations (gains/losses)
         * Database updated
         */
    }
}
