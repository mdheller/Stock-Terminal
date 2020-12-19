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
		System.out.print("Enter stock symbol: ");
		String symbol = sc2.next().toUpperCase();
		callQuote(symbol, key);
		callNews(symbol, key, sc2);
		
		sc1.close();
		sc2.close();		
	}
	
	// callQuote: Calls Yahoo API --> get stock symbol, current price, and previous close
	public static void callQuote(String symbol, String key) throws Exception {
		HttpResponse<String> response = Unirest.get("https://yahoo-finance-low-latency.p.rapidapi.com/v6/finance/quote?symbols=" + symbol) 
				.header("x-rapidapi-key", key)
				.header("x-rapidapi-host", "yahoo-finance-low-latency.p.rapidapi.com")
				.asString();
		
		String json = response.getBody();	// Get the response 

		
		/* Getting objects from JSON object & JSON array
		 * Has to be an easier way to parse nested JSON instead of creating multiple
		 * objects each time
		 */
		
//		System.out.println(json);
		
		JSONObject obj = new JSONObject(json);
		obj = new JSONObject(obj.get("quoteResponse").toString());
		JSONArray arr = new JSONArray(obj.get("result").toString());
		obj = new JSONObject(arr.get(0).toString());
		/* The keyset contains alot of information that can be 
		 * extracted from the JSON object
		 * obj.keySet()
		 */
		
		System.out.println("Symbol: " + obj.get("symbol"));
		System.out.println("Percent change: " + obj.get("regularMarketChangePercent"));
		System.out.println("Open price: " + obj.get("regularMarketOpen"));
		System.out.println("Current price: " + obj.get("regularMarketPrice"));
		System.out.println("Previous close: " + obj.get("regularMarketPreviousClose"));
		System.out.println("Market Cap: " + obj.get("marketCap"));
		System.out.println("Volume: " + obj.get("regularMarketVolume"));
		System.out.println("Exchange: " + obj.get("exchange"));
		System.out.println("Shares outstanding: " + obj.get("sharesOutstanding"));
		

		Unirest.shutDown();
	}
	
	// callNews: Calls Yahoo API --> get news headlines and story summary
	public static void callNews(String symbol, String key, Scanner sc2) {
		HttpResponse<String> response = Unirest.get("https://yahoo-finance-low-latency.p.rapidapi.com/v2/finance/news?symbols=" + symbol)
				.header("x-rapidapi-key", key)
				.header("x-rapidapi-host", "yahoo-finance-low-latency.p.rapidapi.com")
				.asString();
		
		String json = response.getBody();	// Get the response
		
//		System.out.println(json);
		/* You can get a summary of the stories as well - summary
		 * URL of story - url
		 * Author - author_name
		 * Time - provider_publish_time
		 * Source - provider_name
		 * Time zone - timeZoneShortName
		 * 
		 */
		JSONObject obj = new JSONObject(json);
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
		
		System.out.print("Select story summary: ");
		int selection = sc2.nextInt();
		obj = new JSONObject(arr.opt(selection - 1).toString());
		System.out.println(obj.get("summary"));
		
		Unirest.shutDown();
	}

}
