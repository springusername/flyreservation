package com.sine.student.flghtreservation.dto;

public class ReservationUpdateRequest {
	private Long id;
	private Boolean ckeckedIn;
	private int numberOfBags;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getCkeckedIn() {
		return ckeckedIn;
	}
	public void setCkeckedIn(Boolean ckeckedIn) {
		this.ckeckedIn = ckeckedIn;
	}
	public int getNumberOfBags() {
		return numberOfBags;
	}
	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}
}
