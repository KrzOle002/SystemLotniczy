package pl.zpo.egzamin.ModelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.zpo.egzamin.Model.Reservation;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO {
    private Long reservationId;

    private ClientDTO client;

    private FlightDTO flight;
    private String place;

    public ReservationDTO(Long reservationId, ClientDTO clientDTO, FlightDTO flightDTO, String place) {
        this.reservationId = reservationId;
        this.client = clientDTO;
        this.flight = flightDTO;
        this.place = place;
    }

    public ReservationDTO(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.client = new ClientDTO(reservation.getClient());
        this.flight = new FlightDTO(reservation.getFlight());
        this.place = reservation.getPlace();
    }
}
