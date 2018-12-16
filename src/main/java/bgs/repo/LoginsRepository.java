package bgs.repo;
import bgs.model.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginsRepository extends CrudRepository<Login, String> {
    Login findByName(String name);
}
