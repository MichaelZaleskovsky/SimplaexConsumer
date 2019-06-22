package com.simplaexconsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DataConsumer {

	private static final String HOST_NAME = "localhost";
	private static final int PORT = 9000;
	private static final int THREAD_PULL_SIZE = 10;
	
	public static void start(int groupBy) {
		
		String[] data = new String[groupBy];
		boolean waiting = true;
		Socket socket;
		BufferedReader input  = new BufferedReader(new InputStreamReader(System.in));
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_PULL_SIZE);  
		int counter = 0;
		String timeStamp;
		
		while (waiting) {
			try {
				socket = new Socket(HOST_NAME, PORT);
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				waiting = false;
			} catch (IOException e) {
				System.out.println("Waiting for input from port"+PORT);
			}
		}
		
		while (true) {
			if(counter == groupBy) {
				timeStamp = "" + new Date().getTime();
				DataAnalyzingTask dataAnalyzingTask = new DataAnalyzingTask(data, timeStamp);
				executor.execute(dataAnalyzingTask);
				data = new String[groupBy];
				counter = 0;
			} else {
				try {
					data[counter++] = input.readLine();
				} catch (IOException e) {
					System.out.println("Problem with reading from port " + PORT);
					break;
				}
			}
		}
	}

}
