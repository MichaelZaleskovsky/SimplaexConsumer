package com.simplaexconsumer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InnerData {
	private int number;
	private BigDecimal floatPart;
	private long part4;
	
	public InnerData(String part3, String part4) {
		number = 1;
		floatPart = new BigDecimal(part3);
		this.part4 = Long.parseLong(part4);
	}

	public void update(String part3, String part4) {
		number++;
		floatPart = floatPart.add(new BigDecimal(part3));
		this.part4 = Long.parseLong(part4);
	}

	@Override
	public String toString() {
		return floatPart.divide(new BigDecimal(number), RoundingMode.HALF_EVEN).toPlainString() + "," + part4;
	}
	
	
}
