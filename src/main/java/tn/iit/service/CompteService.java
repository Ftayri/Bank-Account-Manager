package tn.iit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tn.iit.dao.CompteDao;
import tn.iit.entity.Compte;
import tn.iit.exception.CompteNotFoundException;

@AllArgsConstructor
@Service
public class CompteService {
	private CompteDao compteDao;
 
	public void save(Compte compte) {
		compteDao.save(compte);
	}
	public void delete(Integer rib) {
		compteDao.deleteById(rib);
	}

	public List<Compte> findAll() {
		return compteDao.findByOrderByRibDesc();
	}
	public Compte findById(Integer rib) {
		return compteDao.findById(rib).orElseThrow(()->new CompteNotFoundException(rib.toString()));
	}
	
	
}
