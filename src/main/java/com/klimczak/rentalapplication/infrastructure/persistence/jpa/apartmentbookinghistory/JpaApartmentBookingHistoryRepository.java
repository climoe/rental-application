package com.klimczak.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.klimczak.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.klimczak.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
class JpaApartmentBookingHistoryRepository implements ApartmentBookingHistoryRepository {

    private final SpringJpaApartmentBookingHistoryRepository springJpaApartmentBookingHistoryRepository;

    JpaApartmentBookingHistoryRepository(SpringJpaApartmentBookingHistoryRepository springJpaApartmentBookingHistoryRepository) {
        this.springJpaApartmentBookingHistoryRepository = springJpaApartmentBookingHistoryRepository;
    }

    @Override
    public boolean existsFor(String apartmentId) {
        return springJpaApartmentBookingHistoryRepository.existsById(apartmentId);
    }

    @Override
    public ApartmentBookingHistory findFor(String apartmentId) {
        return springJpaApartmentBookingHistoryRepository.findById(apartmentId).get();
    }

    @Override
    public void save(ApartmentBookingHistory apartmentBookingHistory) {
        springJpaApartmentBookingHistoryRepository.save(apartmentBookingHistory);
    }
}
