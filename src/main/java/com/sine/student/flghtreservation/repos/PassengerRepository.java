package com.sine.student.flghtreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sine.student.flghtreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
