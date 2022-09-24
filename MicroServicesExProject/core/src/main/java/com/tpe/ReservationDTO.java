package com.tpe;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.tpe.enums.ReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReservationDTO {
	
		private Long id;

		private Long carId;

		private LocalDateTime pickUpTime;
		
		private LocalDateTime dropOffTime;
		
		private String pickUpLocation;
		
		private String dropOffLocation;

		private ReservationStatus status;

		private Double totalPrice;
		
		public Long getTotalHours(LocalDateTime pickUpTime,LocalDateTime dropOffTime) {
			return ChronoUnit.HOURS.between(pickUpTime, dropOffTime);
		}
		
	}

	
