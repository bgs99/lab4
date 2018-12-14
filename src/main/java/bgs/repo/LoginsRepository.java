package bgs.repo;
import bgs.model.Logins;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LoginsRepository extends CrudRepository<Logins, String> {
    Logins findByName(String name);
}
