package tn.iit.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.iit.dao.BankAccountDao;
import tn.iit.entity.BankAccount;
import tn.iit.entity.Client;
import tn.iit.exception.CompteNotFoundException;

import java.util.List;

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

    public List<BankAccount> findByClient(Client client){ return bankAccountDao.findByClient(client);}
    public BankAccount findById(Integer rib) {
        return bankAccountDao.findById(rib).orElseThrow(() -> new CompteNotFoundException(rib.toString()));
    }

    public BankAccount editBalance(Integer rib, float balance) {
        BankAccount bankAccount = bankAccountDao.findById(rib).orElseThrow(() -> new CompteNotFoundException(rib.toString()));
        bankAccount.setBalance(balance);
        bankAccountDao.save(bankAccount);
        return bankAccount;

    }


}
