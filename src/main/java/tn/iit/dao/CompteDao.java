package tn.iit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iit.entity.Client;
import tn.iit.entity.Compte;

public interface CompteDao extends JpaRepository<Compte, Integer> {

	List<Compte> findByOrderByRibDesc();

	List<Compte> findByClient(Client client);

	List<Compte> findByClientCin(String cin);

}
