package project.shimozukuri.banking.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.shimozukuri.banking.entities.bank.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    @Query(value = """
            SELECT *
            FROM banking.payments
            WHERE sender_id = :id
            OR recipient_id = :id
            """, nativeQuery = true)
    List<Payment> findAllByMoneyAccountId(Long id);
}
