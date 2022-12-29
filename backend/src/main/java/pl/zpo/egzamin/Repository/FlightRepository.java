package pl.zpo.egzamin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zpo.egzamin.Model.Flight;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository <Flight, Long> {

    List<Flight> findAll();
    @Query(value = "SELECT * FROM flight WHERE flight.flight_date = CAST(:date AS DATE) AND flight.flight_from = :from AND flight.flight_to = :to", nativeQuery = true)
    List<Flight> findByParams(@Param("from") String from, @Param("to") String to, @Param("date") String date);

    Flight findByFlightId(Long id);

}
