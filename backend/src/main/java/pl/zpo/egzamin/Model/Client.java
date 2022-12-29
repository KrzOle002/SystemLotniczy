package pl.zpo.egzamin.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long clientId;
    private String name;
    private String surname;

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
