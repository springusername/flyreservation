package com.sine.student.flghtreservation.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sine.student.flghtreservation.entities.Reservation;

import ch.qos.logback.classic.Logger;

@Component
public class PDFGenerator {
	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(PDFGenerator.class);

	public void generateItinerary(Reservation reservation, String filePath) {
		LOGGER.info("Inside generate itinerary");
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			document.add(generateTable(reservation));
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
		LOGGER.error("Exception in generateItinerary " + e);
		}
	}

	private PdfPTable generateTable(Reservation reservation) {
		// TODO Auto-generated method stub
		PdfPTable table = new PdfPTable(2);
		
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Flight Itinerary"));
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Flight details"));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("Airelines");
		table.addCell(reservation.getFlight().getOperatingAirlines());
		table.addCell("Departure City");
		table.addCell(reservation.getFlight().getDepartureCity());
		table.addCell("Arrival City");
		table.addCell(reservation.getFlight().getArrivalCity());
		table.addCell("Flight Number");
		table.addCell(reservation.getFlight().getFlightNumber());
		table.addCell("Departure Date");
		table.addCell(reservation.getFlight().getDateOfDeparture().toString());
		table.addCell("Departure Time");
		table.addCell(reservation.getFlight().getEstimatedDepartureTime().toString());
		
		cell = new PdfPCell(new Phrase("Passenger details"));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("First Name");
		table.addCell(reservation.getPassenger().getFirstName());
		
		table.addCell("Last Name");
		table.addCell(reservation.getPassenger().getLastName());
		
		table.addCell("Email");
		table.addCell(reservation.getPassenger().getEmail());
		
		
		table.addCell("Phone");
		table.addCell(reservation.getPassenger().getPhone());
		
		
		return table;
	}
}
