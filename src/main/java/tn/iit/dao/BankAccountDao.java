package tn.iit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.entity.BankAccount;
import tn.iit.entity.Client;

import java.util.List;

public interface BankAccountDao extends JpaRepository<BankAccount, Integer> {

    List<BankAccount> findByOrderByRibDesc();

    List<BankAccount> findByClient(Client client);
}
