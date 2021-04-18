package com.sine.student.flghtreservation.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sine.student.flghtreservation.entities.Flight;
import com.sine.student.flghtreservation.repos.FlightRepository;

import ch.qos.logback.classic.Logger;

@Controller
public class FlightController {
	@Autowired
	FlightRepository flightRepository;
	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(FlightController.class);
	@RequestMapping("/findFlights")
	public String findFlight(@RequestParam("from") String from, @RequestParam("to")String to,@RequestParam("departureDate") @DateTimeFormat(pattern="MM-dd-yyyy") Date departureDate, ModelMap modelMap) {
		LOGGER.info("Inside findFlights()"+from + " to " + to +"departure date " + departureDate);
		List<Flight> flights = flightRepository.findFlight(from,to,departureDate);
		LOGGER.info("Flights found are" + flights.toString());

		modelMap.addAttribute("flights",flights);
		return "displayFlights";
	}
	
	@RequestMapping("admin/showAddFlight")
	public String showAddFlight()
	{
		return "addFlight";
	}
}
