package com.sine.student.flghtreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sine.student.flghtreservation.entities.Flight;
import com.sine.student.flghtreservation.entities.Reservation;
import com.sine.student.flghtreservation.entities.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
