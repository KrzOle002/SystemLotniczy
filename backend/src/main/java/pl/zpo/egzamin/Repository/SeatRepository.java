package pl.zpo.egzamin.Repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zpo.egzamin.Model.Flight;
import pl.zpo.egzamin.Model.Seat;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {

    List<Seat> findByFlight_FlightId(Long id);
}
