package com.stock.build;

import kong.unirest.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import org.json.*;

public class EconomicData {
	
	/* Static for now
	 * Might change to an object class
	 * So that instance can be created
	 * May not be necessary
	 */
	static void gdp(String dataType, String seriesId, boolean recent) throws Exception {
		String filename = "/Users/jasonmoreau/Desktop/API Keys/fred.txt";
		File file = new File(filename);
		FileInputStream in = new FileInputStream(file);
		byte [] data = new byte [32];
		in.read(data);
		String key = new String(data);
		
		HttpResponse<String> response = Unirest.get("https://api.stlouisfed.org/fred/" + dataType)
						.queryString("series_id", seriesId)
						.queryString("api_key", key)
						.queryString("file_type", "json")
						.asString();
		
		JSONObject obj = new JSONObject(response.getBody());
		JSONArray arr = new JSONArray(obj.get("observations").toString());
		
		
		// The latest GDP observation in the series
		if(recent == true){
			obj = new JSONObject(arr.get(arr.length() - 1).toString());	
		}
		
		System.out.println("\n" + obj.get("date") + " to " + obj.get("realtime_start"));
		System.out.println("Billions of Chained 2012 Dollars,\n" + 
				"Seasonally Adjusted Annual Rate: " + obj.get("value") + "\n");
			
		in.close();
	}

}
