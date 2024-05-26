package project.shimozukuri.banking.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.shimozukuri.banking.entities.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = """
            SELECT exists(
            SELECT 1
            FROM banking.users_emails
            WHERE email = :email)
            """, nativeQuery = true)
    boolean existsEmail(@Param("email") String email);

    @Query(value = """
            SELECT exists(
            SELECT 1
            FROM banking.users_phone_numbers
            WHERE phone_number = :phoneNumber)
            """, nativeQuery = true)
    boolean existsPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(value = """
            SELECT *
            FROM banking.users_emails
            WHERE user_id = :id
            """, nativeQuery = true)
    List<String> findAllEmailsById(@Param("id") Long id);

    @Query(value = """
            SELECT *
            FROM banking.users_phone_numbers
            WHERE user_id = :id
            """, nativeQuery = true)
    List<String> findAllPhoneNumbersById(@Param("id") Long id);
}
