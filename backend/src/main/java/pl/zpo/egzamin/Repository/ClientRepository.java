package pl.zpo.egzamin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zpo.egzamin.Model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{
}
