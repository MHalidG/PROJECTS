package com.hillrent.dto.mapper;

import org.mapstruct.Mapper;

import com.hillrent.domain.Reservation;
import com.hillrent.dto.request.ReservationRequest;

@Mapper(componentModel="spring")
public interface ReservationMapper {

	Reservation reservationRequestToReservation(ReservationRequest reservationRequest);
	
}
