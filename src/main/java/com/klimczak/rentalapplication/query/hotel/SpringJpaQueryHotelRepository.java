package com.klimczak.rentalapplication.query.hotel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringJpaQueryHotelRepository extends CrudRepository<HotelReadModel, String> {}
