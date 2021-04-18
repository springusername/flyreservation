package com.sine.student.flghtreservation.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sine.student.flghtreservation.dto.ReservationUpdateRequest;
import com.sine.student.flghtreservation.entities.Reservation;
import com.sine.student.flghtreservation.repos.ReservationRepository;
import com.sine.student.flghtreservation.util.EmailUtil;

import ch.qos.logback.classic.Logger;

@RestController
@CrossOrigin
public class ReservationRestController {
	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(ReservationRestController.class);

	@Autowired
	ReservationRepository reservationRepository;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation");
		return reservationRepository.findById(id).get();
	}
	@RequestMapping("/reservations")
	public Reservation updateReservation (@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation");

		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCkeckedIn());
		LOGGER.info("Saving Reservation");

		return reservationRepository.save(reservation);		
	}
}
