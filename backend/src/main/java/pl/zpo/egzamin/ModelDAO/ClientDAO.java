package pl.zpo.egzamin.ModelDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zpo.egzamin.Model.Client;
import pl.zpo.egzamin.Repository.ClientRepository;

@Service
public class ClientDAO {
    @Autowired
    ClientRepository clientRepository;

    public Client saveClient (Client client){
        return clientRepository.save(client);
    }
}
