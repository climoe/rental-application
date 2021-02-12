package com.klimczak.rentalapplication.infrastructure.persistence.jpa.booking;

import com.klimczak.rentalapplication.domain.apartment.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringJpaBookingRepository extends CrudRepository<Booking, UUID> { }
