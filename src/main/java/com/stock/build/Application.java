/* Stock Terminal 
 * Bloomberg Terminal like application
 * Retrieves data from Yahoo Finance API
 * Retrieves data from FRED (St. Louis Federal Reserve) API
 * Makes stock predictions based on your portfolio
 */

package com.stock.build;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) throws Exception {
		new Terminal().run();
	}
}
