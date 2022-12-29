package pl.zpo.egzamin.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.zpo.egzamin.Model.Seat;


@NoArgsConstructor
@Setter
@Getter
public class SeatDTO {

    private Long seatId;
    private int number;
    private boolean nearWindow;
    private boolean nearHallway;

    private int seatRow;
    private int seatColumn;

    private boolean busy;

    public SeatDTO(Seat seat){
        this.seatId = seat.getSeatId();
        this.number = seat.getNumber();
        this.nearHallway = seat.isNearHallway();
        this.nearWindow = seat.isNearWindow();
        this.seatRow = seat.getSeatRow();
        this.seatColumn = seat.getSeatColumn();
        this.busy = seat.isBusy();
    }


    public SeatDTO(Long seatId, int number, boolean nearWindow, boolean nearHallway, int seatRow, int seatColumn, boolean busy) {
        this.seatId = seatId;
        this.number = number;
        this.nearWindow = nearWindow;
        this.nearHallway = nearHallway;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.busy = busy;
    }
}


