package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Log {

	public File logFile = new File("Log.txt");
	public double totalSales = 0;
	public Scanner logScanner = new Scanner(System.in);
	public Date date = new Date();
	public SimpleDateFormat simpleDate = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

	public void salesLog(String log) throws FileNotFoundException{

		try(PrintWriter writer =  new PrintWriter(new FileOutputStream (logFile,true))) {


			writer.println(simpleDate.format(date) + " " + log);
		}



	}


}
