package pl.zpo.egzamin.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Seat {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long seatId;
    private int number;
    private boolean nearWindow;
    private boolean nearHallway;
    private boolean busy;

    private int seatRow;
    private int seatColumn;
    @ManyToOne
    private Flight flight;


    public Seat(int number, boolean nearWindow, boolean nearHallway, boolean busy, int seatRow, int seatColumn, Flight flight) {
        this.number = number;
        this.nearWindow = nearWindow;
        this.nearHallway = nearHallway;
        this.busy = busy;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.flight = flight;
    }
}
