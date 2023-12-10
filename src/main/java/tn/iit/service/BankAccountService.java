package tn.iit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tn.iit.dao.BankAccountDao;
import tn.iit.entity.BankAccount;
import tn.iit.exception.CompteNotFoundException;

@AllArgsConstructor
@Service
public class BankAccountService {
	private BankAccountDao bankAccountDao;
 
	public void save(BankAccount bankAccount) {
		bankAccountDao.save(bankAccount);
	}
	public void delete(Integer rib) {
		bankAccountDao.deleteById(rib);
	}

	public List<BankAccount> findAll() {
		return bankAccountDao.findByOrderByRibDesc();
	}
	public BankAccount findById(Integer rib) {
		return bankAccountDao.findById(rib).orElseThrow(()->new CompteNotFoundException(rib.toString()));
	}

	public BankAccount editBalance(Integer rib,float balance){
		BankAccount x= bankAccountDao.findById(rib).orElseThrow(()->new CompteNotFoundException(rib.toString()));
		x.setBalance(balance);
		bankAccountDao.save(x);
		return x;

	}
	
	
}
