package tn.iit.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.entity.Client;

import java.util.List;

public interface ClientDao extends JpaRepository<Client, String> {
    Page<Client> findByCinContainingOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrAddressContainingIgnoreCase(
            String cin, String firstName, String lastName, String address, Pageable pageable
    );

    List<Client> findByCinContainingOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrAddressContainingIgnoreCase(
            String cin, String firstName, String lastName, String address
    );

    long countByCinContainingOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String cin, String firstName, String lastName, String address);
}
