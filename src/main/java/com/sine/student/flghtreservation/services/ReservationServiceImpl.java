package com.sine.student.flghtreservation.services;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sine.student.flghtreservation.controllers.ReservationController;
import com.sine.student.flghtreservation.dto.ReservationRequest;
import com.sine.student.flghtreservation.entities.Flight;
import com.sine.student.flghtreservation.entities.Passenger;
import com.sine.student.flghtreservation.entities.Reservation;
import com.sine.student.flghtreservation.repos.FlightRepository;
import com.sine.student.flghtreservation.repos.PassengerRepository;
import com.sine.student.flghtreservation.repos.ReservationRepository;
import com.sine.student.flghtreservation.util.EmailUtil;
import com.sine.student.flghtreservation.util.PDFGenerator;

import ch.qos.logback.classic.Logger;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	@Value("${com.sine.student.flghtreservation.itinerary.dirpath}")
	private String ITENERARY_DIR = "D:\\Developpement_Web\\Spring\\mailInfo\\reservation";
	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(ReservationService.class);

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		// TODO Auto-generated method stub
		LOGGER.info("Inside bookFlight");

		// Make Paymentrequest.getCardNumber();
		

		Long flightId = request.getFlightId();
		LOGGER.info("Fetching flight for id: " + flightId);

		Flight flight = flightRepository.findById(flightId).get();
		Passenger passenger = new Passenger ();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setMiddleName(request.getPassengerMiddleName());

		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving passenger" + passenger.toString());

		Passenger passengerSaved = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation ();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		
		LOGGER.info("Saving reservation" + reservation.toString());

		Reservation reservationSaved = reservationRepository.save(reservation);
		String filePath =  ITENERARY_DIR+reservationSaved.getId()+".pdf";
		
		LOGGER.info("Generation of the itinerary");

		pdfGenerator.generateItinerary(reservation,filePath );
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		LOGGER.info("Email sent");
		return reservationSaved;
	}

}
