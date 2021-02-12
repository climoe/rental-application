package com.klimczak.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.klimczak.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringJpaApartmentBookingHistoryRepository extends CrudRepository<ApartmentBookingHistory, String> {}
