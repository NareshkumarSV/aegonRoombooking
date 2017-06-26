package com.aegon.booking.roombookings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aegon.booking.roombookings.model.RoomEntity;

public interface RoomEntityRepository extends JpaRepository<RoomEntity, Long> {

}
