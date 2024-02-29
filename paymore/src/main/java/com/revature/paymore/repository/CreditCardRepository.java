package com.revature.paymore.repository;

import com.revature.paymore.model.Address;
import com.revature.paymore.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {


}
