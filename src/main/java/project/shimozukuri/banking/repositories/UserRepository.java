package project.shimozukuri.banking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.shimozukuri.banking.entities.user.User;

import java.util.Optional;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
