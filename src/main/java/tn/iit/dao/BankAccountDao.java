package tn.iit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iit.entity.BankAccount;
import tn.iit.entity.Client;

public interface BankAccountDao extends JpaRepository<BankAccount, Integer> {

	List<BankAccount> findByOrderByRibDesc();

	List<BankAccount> findByClient(Client client);

	List<BankAccount> findByClientCin(String cin);

}
