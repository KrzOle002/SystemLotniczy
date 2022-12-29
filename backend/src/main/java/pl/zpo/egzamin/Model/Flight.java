package pl.zpo.egzamin.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Flight {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long flightId;
    private String flightFrom;
    private String flightTo;
    private int price;
    @OneToMany(mappedBy = "flight")
    private List<Seat> seats = new ArrayList<>();
    private LocalDate flightDate;
    @OneToMany(mappedBy = "flight")
    List<Reservation> reservations = new ArrayList<>();

    public Flight(String flightFrom, String flightTo, int price, LocalDate flightDate) {
        this.flightFrom = flightFrom;
        this.flightTo = flightTo;
        this.price = price;
        this.flightDate = flightDate;
    }
}
