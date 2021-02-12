package com.klimczak.rentalapplication.query.apartment;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class QueryApartmentRepository {

    private final SpringQueryApartmentRepository springQueryApartmentRepository;
    private final SpringQueryApartmentBookingHistoryRepository springQueryApartmentBookingHistoryRepository;

    public QueryApartmentRepository(
            SpringQueryApartmentRepository springQueryApartmentRepository,
            SpringQueryApartmentBookingHistoryRepository springQueryApartmentBookingHistoryRepository) {

        this.springQueryApartmentRepository = springQueryApartmentRepository;
        this.springQueryApartmentBookingHistoryRepository = springQueryApartmentBookingHistoryRepository;
    }

    public Iterable<ApartmentReadModel> findAll() {
        return springQueryApartmentRepository.findAll();
    }

    public ApartmentDetails findById(String id) {

        Optional<ApartmentReadModel> found = springQueryApartmentRepository.findById(UUID.fromString(id));

        return found
                .flatMap(f -> springQueryApartmentBookingHistoryRepository.findById(id))
                .map(apartmentBookingHistoryReadModel -> ApartmentDetails.withHistory(found.get(), apartmentBookingHistoryReadModel))
                .orElseGet(ApartmentDetails::notExisting);
    }
}
