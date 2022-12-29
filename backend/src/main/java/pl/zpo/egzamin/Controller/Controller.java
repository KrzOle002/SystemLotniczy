package pl.zpo.egzamin.Controller;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import pl.zpo.egzamin.Model.Client;
import pl.zpo.egzamin.Model.Flight;
import pl.zpo.egzamin.Model.Reservation;
import pl.zpo.egzamin.Model.Seat;
import pl.zpo.egzamin.ModelDAO.ClientDAO;
import pl.zpo.egzamin.ModelDAO.FlightDAO;
import pl.zpo.egzamin.ModelDAO.ReservationDAO;
import pl.zpo.egzamin.ModelDAO.SeatDAO;
import pl.zpo.egzamin.ModelDTO.PostedReservation;
import pl.zpo.egzamin.ModelDTO.ReservationDTO;
import pl.zpo.egzamin.ModelDTO.SeatDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Slf4j
@Log
public class Controller {

    private ClientDAO clientDAO;
    private ReservationDAO reservationDAO;
    private FlightDAO flightDAO;
    private SeatDAO seatDAO;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        fillSeats();
    }

    public void fillSeats(){

        Flight flight = new Flight("Bydgoszcz","London",2000, LocalDate.parse("2022-07-08"));
        Flight flight2 = new Flight("Bydgoszcz","Liverpool",3000, LocalDate.parse("2022-07-08"));
        Flight flight3 = new Flight("Torun","London",3000, LocalDate.parse("2022-07-08"));
        Flight flight4 = new Flight("Torun","Liverpool",4000, LocalDate.parse("2022-07-08"));
        flight = flightDAO.saveFlight(flight);
        flight2 = flightDAO.saveFlight(flight2);
        flight3 = flightDAO.saveFlight(flight3);
        flight4 = flightDAO.saveFlight(flight4);

        fillSeat(flight);
        fillSeat(flight2);
        fillSeat(flight3);
        fillSeat(flight4);
    }

    public void fillSeat(Flight flight) {
        int row = 0;
        int column = 0;
        for (int i = 1; i < 91; i++) {
            Seat seat = new Seat();

            if (column > 5) {
                row++;
                column = 0;
            }
            seat.setSeatRow(row);
            seat.setSeatColumn(column);

            if (column == 2 || column == 3) {
                seat.setNumber(i);
                seat.setNearHallway(true);
                seat.setNearWindow(false);
            } else if (column == 1 || column == 4) {
                seat.setNumber(i);
                seat.setNearHallway(false);
                seat.setNearWindow(false);
            } else {
                seat.setNumber(i);
                seat.setNearHallway(false);
                seat.setNearWindow(true);
            }
            column++;
            seat.setFlight(flight);
            seat = seatDAO.saveSeat(seat);
        }
    }

    @Autowired
    public Controller(ClientDAO clientDAO, ReservationDAO reservationDAO, FlightDAO flightDAO,SeatDAO seatDAO) {
        this.clientDAO = clientDAO;
        this.reservationDAO = reservationDAO;
        this.flightDAO = flightDAO;
        this.seatDAO = seatDAO;
    }

    @GetMapping("/test")
    public String getTestInfo(){
        return "Test";
    }

    @GetMapping("/reservations")
    public List<ReservationDTO> findAllReservations (){
        return reservationDAO.getAllReservation();
    }

    @GetMapping("/seats/{flightId}")
    public List<SeatDTO> findAllSeats ( @PathVariable Long flightId){
        return seatDAO.findAllSeats(flightId);
    }

    @PostMapping("/reservation")
    public String saveReservation(@RequestBody PostedReservation postedReservation){
        if(postedReservation.getFlightFrom().equals(postedReservation.getFlightTo())){
            return "Start and End of flight must be different";
        }
        else{
            List<Flight> findFlights = flightDAO.findFlightByParam(postedReservation.getFlightFrom(),
                    postedReservation.getFlightTo(),
                    postedReservation.getFlightDate().toString());
            if(findFlights.isEmpty()){
                return "This flight don't exist";
            }
            else{
                Flight findFlight = findFlights.stream()
                        .filter(flight -> flight.getSeats().stream()
                                .filter(seat -> seat.isBusy() == false).count() >= postedReservation.getSeats())
                        .sorted(Comparator.comparingInt(Flight::getPrice))
                        .findFirst().orElse(null);

                List<Seat> seats = findFlight.getSeats();

                List<Seat> reservedSeats = new ArrayList<>();
                boolean firstFound = false;
                int i = 0;
                for (Seat seat: seats) {
                    if(postedReservation.getSeats() > reservedSeats.size()){
                        if(firstFound==false){
                            if(!seat.isBusy()
                                    && postedReservation.isNearWindow() == seat.isNearWindow()
                                    && postedReservation.isNearHallway()== seat.isNearHallway()){
                                firstFound = true;
                                reservedSeats.add(seat);
                            }
                        }
                        else{
                            if(reservedSeats.get(i).getSeatColumn()<seat.getSeatColumn() && seat.isBusy()==false){
                                reservedSeats.add(seat);
                            }
                            
                            else {
                                if (reservedSeats.get(i).getSeatRow() < seat.getSeatRow() && seat.isBusy() == false) {
                                    if (reservedSeats.size() > 1) {
                                        reservedSeats.add(seat);
                                    }
                                    else{
                                        firstFound=false;

                                    }
                                }
                                else{
                                    firstFound=false;

                                }
                            }
                            i++;
                        }
                    }
                    else{
                        break;
                    }
                }

                if(findFlight == null || reservedSeats == null){
                    return "Not found flight with this parameters";
                }
                else{
                    Client client = new Client(postedReservation.getName(), postedReservation.getSurname());
                    clientDAO.saveClient(client);

                    String places = reservedSeats.stream()
                            .map(n -> String.valueOf(n.getNumber()))
                            .collect(Collectors.joining(", "));
                    for( Seat seat : reservedSeats){
                        seat.setBusy(true);
                        seatDAO.saveSeat(seat);
                    }

                    Reservation reservation = new Reservation(client,findFlight, places );
                    reservationDAO.saveReservation(reservation);
                    return "Reserved";
                }
            }
        }
    }
}
