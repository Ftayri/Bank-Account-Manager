package tn.iit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.entity.BankAccount;

import java.util.List;

public interface BankAccountDao extends JpaRepository<BankAccount, Integer> {

    List<BankAccount> findByOrderByRibDesc();

}
