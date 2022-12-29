package pl.zpo.egzamin.ModelDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zpo.egzamin.Model.Flight;
import pl.zpo.egzamin.Repository.FlightRepository;

import java.util.List;

@Service
public class FlightDAO {
    @Autowired
    FlightRepository flightRepository;

    public Flight saveFlight (Flight flight){
        return flightRepository.save(flight);
    }

    public Flight findFlightById(Long id){
        return flightRepository.findByFlightId(id);
    }
    public List<Flight> findFlightByParam(String from,String to,String date){
        return flightRepository.findByParams(from,to,date);
    }
}
