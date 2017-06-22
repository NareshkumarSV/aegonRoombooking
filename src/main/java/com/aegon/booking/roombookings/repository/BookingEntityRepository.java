package com.aegon.booking.roombookings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aegon.booking.roombookings.model.BookingEntity;

public interface BookingEntityRepository extends JpaRepository<BookingEntity, Long> {

}
