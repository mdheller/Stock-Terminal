package test;

import kong.unirest.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import org.json.*;

public class FedAPI {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		String filename = "/Users/jasonmoreau/Desktop/API Keys/fred.txt";
		File file = new File(filename);
		FileInputStream in = new FileInputStream(file);
		byte [] data = new byte [32];
		in.read(data);
		String key = new String(data);
		
		// Real GDP
		// GDPC1
		// data type: series/observations
		// series id: gdpc1
		System.out.print("Data type: ");
		String type = input.nextLine().toLowerCase();
		System.out.print("Series ID: ");
		String seriesId = input.nextLine().toUpperCase();

		HttpResponse<String> response = Unirest.get("https://api.stlouisfed.org/fred/" + type)
						.queryString("series_id", seriesId)
						.queryString("api_key", key)
						.queryString("file_type", "json")
						.asString();
		
		String json = response.getBody();
		
		JSONObject obj = new JSONObject(json);
		JSONArray arr = new JSONArray(obj.get("observations").toString());
		// The latest GDP observation in the series
		obj = new JSONObject(arr.get(294).toString());
		// Prints the object in JSON format
		System.out.println(obj.toString(1));
			
		in.close();
		input.close();
		
	}

}
