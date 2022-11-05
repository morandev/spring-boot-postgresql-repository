package com.morandev.repoconpostgresql.repository;

import com.morandev.repoconpostgresql.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByArrived(boolean arrived);
    List<Flight> findByRouteContaining(String title);
}
