package pl.zpo.egzamin.ModelDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import pl.zpo.egzamin.Model.Reservation;
import pl.zpo.egzamin.Model.Seat;
import pl.zpo.egzamin.ModelDTO.SeatDTO;
import pl.zpo.egzamin.Repository.SeatRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatDAO {
    @Autowired
    SeatRepository seatRepository;

    public Seat saveSeat (Seat seat){
        return seatRepository.save(seat);
    }

    public List<SeatDTO> findAllSeats(Long id){
        return  seatRepository.findByFlight_FlightId(id).stream().map(SeatDTO::new).collect(Collectors.toList());
    }




}
