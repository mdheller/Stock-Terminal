/* Stock Terminal 
 * Program retrieves current stock prices
 * Obtains data from Yahoo Finance
 */

package com.stock.build;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Terminal {
	public static void main(String[] args) throws Exception {
		File file = new File("/Users/jasonmoreau/Desktop/API Keys/rapidapi.txt");
		FileInputStream in = new FileInputStream(file);
		byte [] arr = new byte[50]; 	// must match size of key length
		in.read(arr);
		Scanner sc = new Scanner(System.in);
		String key = new String(arr);
		
		boolean start = true;
		String selection = "";
		programTitle();
		
		while(start) {
			mainMenu();
			selection = sc.nextLine();
			if(selection.matches("1")) {
				quoteTitle();
				Stocks.callQuote(key, sc);
			} else if(selection.matches("2")) {
				newsTitle();
				Stocks.callNews(key, sc);
			} else if(selection.matches("3")) {
				economicTitle();
				EconomicData.gdp(sc);
			} else if(selection.matches("4")) {
				tradingTitle();
				trade();
				// as a placeholder
			} else if(selection.matches("5")) {
				portfolioTitle();
				portfolio();
				// as a placeholder
			} else if(selection.matches("6")) {
				System.out.println("\nGoodbye");
				start = false;
			}
		}		
		
		sc.close();
		in.close();		
	}
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static void programTitle() {
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
	
	public static void mainMenu() {
		System.out.println("*** Main Menu ***\n ");
		System.out.println("1. Get Quotes");
		System.out.println("2. Latest News");
		System.out.println("3. Economic Data");
		System.out.println("4. Start Trading --> as part of a database");
		System.out.println("5. View Portfolio --> as part of a database");
		System.out.println("6. Quit\n");
		System.out.print("Selection: ");
	}
	
	public static void quoteTitle() {
		clearScreen();
		System.out.println("\n*** Stock Quotes *** \n");
	}
	
	
	public static void tradingTitle() {
		clearScreen();
		System.out.println("\n*** Trading Dashboard *** ");
		
	}
	
	public static void newsTitle() {
		clearScreen();
		System.out.println("\n*** Latest News *** \n");
	}
	
	public static void economicTitle() {
		clearScreen();
		System.out.println("\n*** Economic Data *** \n");
	}
	
	public static void trade() {
		System.out.println("Placeholder\n");
		// Account creation / login screen
		// Menu options (buy/sell/trade, fund account, etc)
	    // Database updated
	}
	
	public static void portfolioTitle() {
		clearScreen();
		System.out.println("\n*** Portfolio *** ");
	}
	
	public static void portfolio() {
		System.out.println("Placeholder\n");
		// Login screen
		// Code to grab all current stock prices and and make calculations (gains/losses)
		// Database updated
	}
	
	

}
