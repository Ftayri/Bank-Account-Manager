package tn.iit;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankAccountManagerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountManagerApplication.class, args);
    }

    @Transactional
    @Override
    public void run(String... args) {

    }

}
