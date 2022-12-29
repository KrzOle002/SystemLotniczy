package pl.zpo.egzamin.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostedReservation {
    String name;
    String surname;
    private String flightFrom;
    private String flightTo;
    private int seats;
    private LocalDate flightDate;
    private boolean nearWindow;
    private boolean nearHallway;
}
