package tn.iit;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.iit.dao.ClientDao;
import tn.iit.dao.CompteDao;

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
    }

}
