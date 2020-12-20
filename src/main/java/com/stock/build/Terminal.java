/* Stock Terminal 
 * Program retrieves current stock prices
 * Obtains data from Yahoo Finance
 */

package com.stock.build;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import kong.unirest.*;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Terminal {
	public static void main(String[] args) throws Exception {
		File file = new File("/Users/jasonmoreau/Desktop/API Keys/rapidapi.txt");
		Scanner sc1 = new Scanner(file);
		Scanner sc2 = new Scanner(System.in);
		String key = sc1.next();
		
		boolean start = true;
		String selection = "";
		programTitle();
		
		while(start) {
			mainMenu();
			selection = sc2.nextLine();
			if(selection.matches("1")) {
				quoteTitle();
				Stocks stock = new Stocks();
				System.out.print("Enter stock symbol: ");
				String symbol = sc2.next().toUpperCase();
				stock.callQuote(symbol, key);
			} else if(selection.matches("2")) {
				Stocks stock = new Stocks();
				String symbol = sc2.next().toUpperCase();
				/* Select story summary
				 * 
				 */
				stock.callNews(symbol, key, sc2);
			} else if(selection.matches("3")) {
				tradingTitle();
				trade();
				start = true;		// as a placeholder
			} else if(selection.matches("4")) {
				portfolioTitle();
				portfolio();
				start = true;		// as a placeholder
			} else if(selection.matches("5")) {
				System.out.println("\nGoodbye");
				start = false;
			}
		}		
		
		sc1.close();
		sc2.close();		
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
		System.out.println("3. Start Trading --> as part of a database");
		System.out.println("4. View Portfolio --> as part of a database");
		System.out.println("5. Quit\n");
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
