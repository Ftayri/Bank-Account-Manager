package tn.iit.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.entity.Client;

public interface ClientDao extends JpaRepository<Client, String> {
    Page<Client> findByCinContainingOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrAddressContainingIgnoreCase(
            String cin, String firstName, String lastName, String address, Pageable pageable
    );

    long countByCinContainingOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String cin, String firstName, String lastName, String address);
}
