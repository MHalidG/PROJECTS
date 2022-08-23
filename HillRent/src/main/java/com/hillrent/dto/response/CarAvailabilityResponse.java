package com.hillrent.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class CarAvailabilityResponse extends HRResponse {
	
	
	private boolean available;
	private double totalPrice;
	
	public CarAvailabilityResponse(boolean available,double totalPrice,String message, boolean success) {
		super(success, message);
		this.available=available;
		this.totalPrice=totalPrice;
		
		
	}
}
