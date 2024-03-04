package com.revature.paymore.repository;
import com.revature.paymore.model.CreditCard;
import com.revature.paymore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {


    List<CreditCard> findByUser(User user);

    Optional<CreditCard> findByUserAndCardNumber(User user, String cardNumber);


}
