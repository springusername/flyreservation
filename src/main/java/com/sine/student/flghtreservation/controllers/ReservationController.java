package com.sine.student.flghtreservation.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sine.student.flghtreservation.dto.ReservationRequest;
import com.sine.student.flghtreservation.entities.Flight;
import com.sine.student.flghtreservation.entities.Reservation;
import com.sine.student.flghtreservation.repos.FlightRepository;
import com.sine.student.flghtreservation.services.ReservationService;

import ch.qos.logback.classic.Logger;

@Controller
public class ReservationController {
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	ReservationService reservationService;
	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(ReservationController.class);

	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation (@RequestParam("flightId") Long flightId,ModelMap modelMap) {
		LOGGER.info("Inside showCompleteReservation flight id is" + flightId);
		Flight flight = flightRepository.findById(flightId).get();
		modelMap.addAttribute("flight",flight);	
		return "completeReservation";
		
	}
	
	@RequestMapping(value="/completeReservation",method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		LOGGER.info("Inside completeReservation " + request.toString());

		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg","Created successfully and the id is "+ reservation.getId());
		return "reservationConfirmation";
		
	}
}
