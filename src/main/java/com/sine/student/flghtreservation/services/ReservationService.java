package com.sine.student.flghtreservation.services;

import com.sine.student.flghtreservation.dto.ReservationRequest;
import com.sine.student.flghtreservation.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);
}