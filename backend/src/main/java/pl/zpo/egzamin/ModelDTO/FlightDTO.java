package pl.zpo.egzamin.ModelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.zpo.egzamin.Model.Flight;
import pl.zpo.egzamin.Model.Reservation;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class FlightDTO {
    private Long flightId;
    private String flightFrom;
    private String flightTo;
    private int price;

    private LocalDate flightDate;

    public FlightDTO(Long flightId, String flightFrom, String flightTo, int price, LocalDate flightDate) {
        this.flightId = flightId;
        this.flightFrom = flightFrom;
        this.flightTo = flightTo;
        this.price = price;
        this.flightDate = flightDate;
    }

    public FlightDTO(Flight flight) {
        this.flightId = flight.getFlightId();
        this.flightFrom = flight.getFlightFrom();
        this.flightTo = flight.getFlightTo();
        this.price = flight.getPrice();

        this.flightDate = flight.getFlightDate();
    }
}
