package com.klimczak.rentalapplication.infrastructure.persistence.jpa.hotelbookinghistory;

import com.klimczak.rentalapplication.domain.hotelbookinghistory.HotelBookingHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringJpaHotelBookingHistoryRepository extends CrudRepository<HotelBookingHistory, String> { }
