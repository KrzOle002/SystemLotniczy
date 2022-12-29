package pl.zpo.egzamin.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long reservationId;
    @OneToOne
    private Client client;
    @ManyToOne
    private Flight flight;

    private String place;

    public Reservation(Client client, Flight flight, String place) {
        this.client = client;
        this.flight = flight;
        this.place = place;
    }
}
