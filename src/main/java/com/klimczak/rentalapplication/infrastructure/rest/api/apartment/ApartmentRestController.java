package com.klimczak.rentalapplication.infrastructure.rest.api.apartment;

import com.klimczak.rentalapplication.application.apartment.ApartmentApplicationService;
import com.klimczak.rentalapplication.query.apartment.ApartmentDetails;
import com.klimczak.rentalapplication.query.apartment.ApartmentReadModel;
import com.klimczak.rentalapplication.query.apartment.QueryApartmentRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/apartment")
public class ApartmentRestController {
    private final ApartmentApplicationService apartmentApplicationService;
    private final QueryApartmentRepository queryApartmentRepository;

    public ApartmentRestController(
            ApartmentApplicationService apartmentApplicationService, QueryApartmentRepository queryApartmentRepository) {
        this.apartmentApplicationService = apartmentApplicationService;
        this.queryApartmentRepository = queryApartmentRepository;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody ApartmentDto apartmentDto) {
        String id = apartmentApplicationService.add(
                apartmentDto.getOwnerId(), apartmentDto.getStreet(), apartmentDto.getPostalCode(), apartmentDto.getHouseNumber(),
                apartmentDto.getApartmentNumber(), apartmentDto.getCity(), apartmentDto.getCountry(), apartmentDto.getDescription(),
                apartmentDto.getRoomsDefinition());

        return ResponseEntity.created(URI.create("/apartment/" + id)).build();
    }

    @PutMapping("/book/{id}")
    public void book(@PathVariable String id, @RequestBody ApartmentBookingDto apartmentBookingDto) {
        apartmentApplicationService.book(
                id, apartmentBookingDto.getTenantId(), apartmentBookingDto.getStart(), apartmentBookingDto.getEnd());
    }

    @GetMapping
    public Iterable<ApartmentReadModel> findAll() {
        return queryApartmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ApartmentDetails findById(@PathVariable String id) {
        return queryApartmentRepository.findById(id);
    }
}
