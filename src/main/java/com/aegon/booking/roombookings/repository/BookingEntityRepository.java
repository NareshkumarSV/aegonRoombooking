/**
 * 
 * @author Nareshkumar
 * Classname: BookingEntityRepository
 *
 */
package com.aegon.booking.roombookings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aegon.booking.roombookings.model.BookingEntity;

/**
 * The Interface BookingEntityRepository which extends JpaRepository 
 * that persists and handles the model BookingEntity.
 */
public interface BookingEntityRepository extends JpaRepository<BookingEntity, Long> {

}
