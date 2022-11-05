package com.morandev.repoconpostgresql.controller;

import com.morandev.repoconpostgresql.model.Flight;
import com.morandev.repoconpostgresql.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam(required = false) String route) {
        try {
            List<Flight> flights = new ArrayList<>();

            if (route == null) {
                flightRepository.findAll().forEach(flights::add);
            } else {
                flightRepository.findByRouteContaining(route).forEach(flights::add);
            }

            if (flights.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(flights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightsById(@PathVariable("id") long id) {
        Optional<Flight> flightData = flightRepository.findById(id);

        if (flightData.isPresent()) {
            return new ResponseEntity<>(flightData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        try {
            Flight _flight = new Flight();

            _flight.setArrived(flight.getArrived());
            _flight.setDateandtime(flight.getDateandtime());
            _flight.setRoute(flight.getRoute());

            _flight = flightRepository.save(_flight);

            return new ResponseEntity<>(_flight, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable("id") long id, @RequestBody Flight flight) {
        Optional<Flight> flightData = flightRepository.findById(id);

        if (flightData.isPresent()) {
            Flight _flight = flightData.get();
            _flight.setRoute(flight.getRoute());
            _flight.setArrived(flight.getArrived());
            _flight.setDateandtime(flight.getDateandtime());

            return new ResponseEntity<>(flightRepository.save(_flight), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFlight(@PathVariable("id") long id) {
        try {
            flightRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAllFlights() {
        try {
            flightRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/published")
    public ResponseEntity<List<Flight>> findByPublished() {
        try {
            List<Flight> flights = flightRepository.findByArrived(false);
            if (flights.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
