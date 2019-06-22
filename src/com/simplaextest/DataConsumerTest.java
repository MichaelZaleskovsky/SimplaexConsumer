package com.simplaextest;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.simplaexconsumer.DataAnalyzingTask;
import com.simplaexconsumer.DataConsumer;
import com.simplaexconsumer.TCPProducer;

public class DataConsumerTest {
	
	static File dir;
	
	@BeforeAll
	static void setup() throws InterruptedException {
		
		dir = new File(DataAnalyzingTask.DIR);
		for (File f : dir.listFiles()) f.delete();
		
		new Thread(() -> DataConsumer.start(5)).start();
		new Thread(() -> TCPProducer.main(null)).start();
		
		while(dir.list().length < 2) {
			Thread.sleep(10);
		}
	}
	
	@Test
	void testDataConsumer0() throws FileNotFoundException {
		String[] expected = {
				"30212888860247708058",
				"3",
				"5fac6dc8-ea26-3762-8575-f279fe5e5f51,0.7626710614484215,1005421520",
				"0977dca4-9906-3171-bcec-87ec0df9d745,0.67949814850663702,1851028776",
				"4d968baa-fe56-3ba0-b142-be9f457c9ff4,0.6532229483547558,1403876285"
		};
		
		String[] actual = new String[expected.length];
		Scanner scanner = new Scanner(dir.listFiles()[0]);
		
		int i = 0;
		
		while (scanner.hasNextLine() && i < expected.length) {
			actual[i++] = scanner.next();
		}
		
		assertArrayEquals(expected, actual);
		
	}

	@Test
	void testDataConsumer1() throws FileNotFoundException {
		String[] expected = {
				"25493180386520262311",
				"2",
				"023316ec-c4a6-3e88-a2f3-1ad398172ada,0.31966046918597868,1579431460",
				"0977dca4-9906-3171-bcec-87ec0df9d745,0.503746107278880039,280709214"
		};
		
		String[] actual = new String[expected.length];
		Scanner scanner = new Scanner(dir.listFiles()[1]);
		
		int i = 0;
		
		while (scanner.hasNextLine() && i < expected.length) {
			actual[i++] = scanner.nextLine();
		}
		
		assertArrayEquals(expected, actual);
		
	}
}

