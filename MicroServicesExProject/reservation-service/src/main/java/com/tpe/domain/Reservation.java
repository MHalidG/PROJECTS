package com.tpe.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tpe.enums.ReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	// Sequence identitye gore daha performansli calisiyor batch islemlerinde. ???
	// Batch islemleri nedir
	private Long id;

	@Column(nullable = false)
	private Long carId;

	@Column(nullable = false)
	private LocalDateTime pickUpTime;
	
	@Column(nullable = false)
	private LocalDateTime dropOffTime;
	
	@Column(length = 150, nullable = false)
	private String pickUpLocation;
	
	@Column(length = 150, nullable = false)
	private String dropOffLocation;

	@Column(nullable = false)
	private ReservationStatus status;
	
	public Long getTotalHours(LocalDateTime pickUpTime,LocalDateTime dropOffTime) {
		return ChronoUnit.HOURS.between(pickUpTime, dropOffTime);
	}
	
}
