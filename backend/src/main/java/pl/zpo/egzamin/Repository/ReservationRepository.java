package pl.zpo.egzamin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zpo.egzamin.Model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository <Reservation, Long> {
}
