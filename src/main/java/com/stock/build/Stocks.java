package com.stock.build;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;


public class Stocks {
	
	// callQuote: Calls Yahoo API --> get stock symbol, current price, and previous close
	public static void callQuote(String key, Scanner sc) throws Exception {
		boolean quoteLoop = true;
		String selection = "";
			 
		while(quoteLoop) { 
			System.out.println("1. Current stock quote");
			System.out.println("2. Historic data\n");
			System.out.print("Selection: ");
			selection = sc.nextLine();
			
			if(selection.matches("1")) {
				clearScreen();
				System.out.println("\n*** Current Stock Quote *** \n");
				System.out.print("Enter stock symbol: ");
				String symbol = sc.next().toUpperCase();
				HttpResponse<String> response = Unirest.get("https://yahoo-finance-low-latency.p.rapidapi.com/v6/finance/quote?symbols=" + symbol) 
						.header("x-rapidapi-key", key)
						.header("x-rapidapi-host", "yahoo-finance-low-latency.p.rapidapi.com")
						.asString();
					
				;	// Get the response 

				/* Getting objects from JSON object & JSON array */
				JSONObject obj = new JSONObject(response.getBody());	// Get the response, store it in JSON object
				obj = new JSONObject(obj.get("quoteResponse").toString());
				JSONArray arr = new JSONArray(obj.get("result").toString());
				obj = new JSONObject(arr.get(0).toString());
				/* The keyset contains alot of information that can be extracted from the JSON object
				 */
				System.out.println("Symbol: " + obj.get("symbol"));
				System.out.println("Percent change: " + obj.get("regularMarketChangePercent"));
				System.out.println("Open price: " + obj.get("regularMarketOpen"));
				System.out.println("Current price: " + obj.get("regularMarketPrice"));
				System.out.println("Previous close: " + obj.get("regularMarketPreviousClose"));
				System.out.println("Market Cap: " + obj.get("marketCap"));
				System.out.println("Volume: " + obj.get("regularMarketVolume"));
				System.out.println("Exchange: " + obj.get("exchange"));
				System.out.println("Shares outstanding: " + obj.get("sharesOutstanding") + "\n");
				
				Unirest.shutDown();
			} else if(selection.matches("2")) {
				clearScreen();
				System.out.println("\n*** Historic Data *** \n");
				System.out.println("\n*** Placeholder *** \n");
			}
			quoteLoop = false;
		}
		sc.nextLine();	// clear scanner
	}
	
	// callNews: Calls Yahoo API --> get news headlines and story summary
	public static void callNews(String key, Scanner sc) {
		System.out.println("Enter stock symbol: ");
		String symbol = sc.nextLine().toUpperCase();
		
		
		HttpResponse<String> response = Unirest.get("https://yahoo-finance-low-latency.p.rapidapi.com/v2/finance/news?symbols=" + symbol)
				.header("x-rapidapi-key", key)
				.header("x-rapidapi-host", "yahoo-finance-low-latency.p.rapidapi.com")
				.asString();
					
		/* You can get a summary of the stories as well - summary
		 * URL of story - url
		 * Author - author_name
		 * Time - provider_publish_time
		 * Source - provider_name
		 * Time zone - timeZoneShortName
		 * 
		 */
		JSONObject obj = new JSONObject(response.getBody());	// Get the response, store it in JSON object
		obj = obj.getJSONObject("Content");		
		JSONArray arr = new JSONArray(obj.get("result").toString());
		 
		for(int i = 0; i < arr.length(); i++) {
			obj = new JSONObject(arr.opt(i).toString());
			LocalDateTime date = LocalDateTime.ofEpochSecond(Long.parseLong(obj.get("provider_publish_time").toString()), 0, ZoneOffset.MIN);
			System.out.println("(" + (i + 1) + ") "+ obj.get("title") + 
					" - " + date.toLocalDate() + " " + date.toLocalTime() + 
					" " + obj.get("timeZoneShortName"));
			System.out.println();
		}
		
		System.out.print("Read story summary? Y or N\n");
		boolean readSummary = (sc.nextLine().toUpperCase().matches("Y")) ? true : false;
		
		if(readSummary) {
			System.out.print("Select a number to select a story: ");
			int selection = sc.nextInt();
			obj = new JSONObject(arr.opt(selection - 1).toString());
			System.out.println(obj.get("summary") + "\n");
			/* Maybe add the ability to select the story 
			 * and visit the webpage
			 */
		}
		
		sc.nextLine();	// clear scanner
			
		Unirest.shutDown();
	}
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

}
