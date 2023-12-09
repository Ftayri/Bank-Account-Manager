package tn.iit;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.iit.dao.ClientDao;
import tn.iit.dao.CompteDao;
import tn.iit.entity.Client;
import tn.iit.entity.Compte;

@SpringBootApplication
public class FirstSpringBootApplication implements CommandLineRunner {

    @Autowired
    private CompteDao compteDao;

    @Autowired
    private ClientDao clientDao;

    public static void main(String[] args) {
        SpringApplication.run(FirstSpringBootApplication.class, args);
    }

    @Transactional // pour le commit
    @Override
    public void run(String... args) throws Exception {
        Client client1 = new Client("057", "Ammar", "Nour");
        clientDao.save(client1);

        Compte compte1 = new Compte(100, client1);
        compteDao.save(compte1);
        Compte compte2 = new Compte(300, client1);
        compteDao.save(compte2);
        // recherche by Rib
        Integer rib = 1;
        Compte compte = compteDao.findById(rib).get();
        System.out.println("Compte = " + compte);
        System.out.println("Client = " + compte.getClient());
        System.out.println("-------------------");
        // recherche by cin
        String cin = "057";
        Client client = clientDao.findById(cin).get();
        System.out.println("client = " + client);
        // unidirectionnelle
        System.out.println("comptes = " + compteDao.findByClient(client));
        System.out.println("comptes = " + compteDao.findByClientCin(cin));
        // bidirectionnelle
        System.out.println("comptes = " + client.getComptes());

    }

}
