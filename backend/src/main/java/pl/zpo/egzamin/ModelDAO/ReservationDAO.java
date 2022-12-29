package pl.zpo.egzamin.ModelDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zpo.egzamin.Model.Reservation;
import pl.zpo.egzamin.ModelDTO.ReservationDTO;
import pl.zpo.egzamin.Repository.ReservationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationDAO {
    @Autowired
    ReservationRepository reservationRepository;

    public Reservation saveReservation (Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<ReservationDTO> getAllReservation(){
        return reservationRepository.findAll().stream().map(ReservationDTO::new).collect(Collectors.toList());
    }
}
