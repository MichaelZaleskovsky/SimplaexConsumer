package com.simplaexconsumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;



public class DataAnalyzingTask implements Runnable {
	
	Logger log = Logger.getLogger(DataAnalyzingTask.class.getName());
	
	public static final String DIR = "./output";
	public static final String FILE_PREFIX = "/simplaex";
	
	private String[] data;
	private String timeStamp;
	private int number;
	private Map<String, InnerData> storage = new HashMap<>();
	private BigInteger summ5 = new BigInteger("0");

	public DataAnalyzingTask(String[] data, String timeStamp) {
		this.data = data;
		this.timeStamp = timeStamp;
	}
	
	@Override
	public void run() {
		String[] parts;
		for(String str : data) {
			try {
				parts = str.split(",");
				InnerData innerData = storage.get(parts[0]);
				summ5 = summ5.add(new BigInteger(parts[4]));
				
				if(innerData == null) {
					innerData = new InnerData(parts[2], parts[3]);
					storage.put(parts[0], innerData);
				} else {
					innerData.update(parts[2], parts[3]);
				}
			} catch (Exception e) {
				log.info("Input stream have incorrect data at time" + timeStamp);
				return;
			}
		}
		
		File dir = new File(DIR);
		dir.mkdir();
		
		StringBuilder sb = new StringBuilder();
		sb.append(DIR)
		  .append(FILE_PREFIX)
		  .append(timeStamp)
		  .append(".txt");
		
		
		String fileName = sb.toString();
		File file = new File(fileName);
		
		try (PrintStream output = new PrintStream(fileName))
		{
			output.println(summ5.toString());
			output.println(storage.keySet().size());
			storage.entrySet()
				   .stream()
				   .map(entry -> {
					   sb.delete(0, sb.length());
					   sb.append(entry.getKey()).append(",").append(entry.getValue().toString());
					   return sb.toString();
				   })
				   .forEach(output::println);
			
			log.info("File successfuly created: " + fileName);

		} catch (FileNotFoundException e) {
			log.info("Can not open file " + fileName);
		}
		
		
	}

}
