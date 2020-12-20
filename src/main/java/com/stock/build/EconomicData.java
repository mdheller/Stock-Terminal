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
	static void gdp(Scanner sc) throws Exception {
		String filename = "/Users/jasonmoreau/Desktop/API Keys/fred.txt";
		File file = new File(filename);
		FileInputStream in = new FileInputStream(file);
		byte [] data = new byte [32];
		in.read(data);
		String key = new String(data);

		/* Might have create options so that user doesn't have to type
		 * in data type and series id
		 * 
		 * Real GDP
		 * GDPC1
		 * data type: series/observations
		 * series id: gdpc1
		 */ 
		System.out.print("Data type: ");
		String type = sc.nextLine().toLowerCase();
		System.out.print("Series ID: ");
		String seriesId = sc.nextLine().toUpperCase();

		HttpResponse<String> response = Unirest.get("https://api.stlouisfed.org/fred/" + type)
						.queryString("series_id", seriesId)
						.queryString("api_key", key)
						.queryString("file_type", "json")
						.asString();
		
		JSONObject obj = new JSONObject(response.getBody());
		JSONArray arr = new JSONArray(obj.get("observations").toString());
		// The latest GDP observation in the series
		obj = new JSONObject(arr.get(294).toString());
		// Prints the object in JSON format
		System.out.println(obj.toString(1));
			
		in.close();
	}

}
