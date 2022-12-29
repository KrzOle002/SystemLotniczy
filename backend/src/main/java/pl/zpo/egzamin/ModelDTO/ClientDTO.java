package pl.zpo.egzamin.ModelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.zpo.egzamin.Model.Client;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
    private Long clientId;
    private String name;
    private String surname;

    public ClientDTO(Long clientId, String name, String surname) {
        this.clientId = clientId;
        this.name = name;
        this.surname = surname;
    }

    public ClientDTO(Client client) {
        this.clientId = client.getClientId();
        this.name = client.getName();
        this.surname = client.getSurname();
    }
}
