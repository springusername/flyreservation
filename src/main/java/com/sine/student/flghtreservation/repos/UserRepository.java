package com.sine.student.flghtreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sine.student.flghtreservation.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
