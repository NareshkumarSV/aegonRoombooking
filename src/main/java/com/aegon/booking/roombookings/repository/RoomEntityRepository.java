/**
 * 
 * @author Nareshkumar
 * Classname: RoomEntityRepository
 *
 */
package com.aegon.booking.roombookings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aegon.booking.roombookings.model.RoomEntity;

/**
 * The Interface RoomEntityRepository which extends JpaRepository 
 * that persists and handles the model RoomEntity.
 */
public interface RoomEntityRepository extends JpaRepository<RoomEntity, Long> {

}
