package com.simplaexconsumer;

public class TCPConsumer {
	
	private static final int GROUP_BY = 1000;
	
	public static void main(String arg[]) {
		
		DataConsumer.start(GROUP_BY);
		
	}
}
