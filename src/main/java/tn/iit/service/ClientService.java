package tn.iit.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tn.iit.dao.ClientDao;
import tn.iit.dto.ClientDto;
import tn.iit.entity.BankAccount;
import tn.iit.entity.Client;
import tn.iit.exception.CompteNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClientService {
    private ClientDao clientDao;

    public void save(Client client) throws DuplicateKeyException {
        Optional<Client> existingClient = clientDao.findById(client.getCin());

        if (existingClient.isPresent()) {
            throw new DuplicateKeyException("Client with the same cin already exists");
        }
        clientDao.save(client);
    }
    public void edit(Client client){
        Optional<Client> existingClient = clientDao.findById(client.getCin());
        if (existingClient.isEmpty()) {
            throw new EntityNotFoundException("No Client with the given CIN exists.");
        }
        clientDao.save(client);
    }
    public void delete(String cin) {
        clientDao.deleteById(cin);
    }

    public List<ClientDto> findAll() {
        return convertClientsToDTOs(clientDao.findAll());
    }

    public Client findById(String cin) {
        return clientDao.findById(cin).orElseThrow(() -> new CompteNotFoundException(cin));
    }

    public List<ClientDto> getClientsForDataTable(int start, int length, String searchValue, String orderDir) {
        List<String> columnNames = Arrays.asList("cin", "fullName", "address", "numberOfAccounts", "totalBalance");
        Pageable pageable = PageRequest.of(start / length, length, orderDir != null ? Sort.Direction.fromString(orderDir) : Sort.Direction.ASC, "cin"

        );
        Page<Client> clientPage = findBySearchValue(searchValue, pageable);
        return convertClientsToDTOs(clientPage.getContent());
    }

    private List<ClientDto> convertClientsToDTOs(List<Client> clients) {
        return clients.stream().map(client -> new ClientDto(client.getCin(), client.getFirstName() + " " + client.getLastName(), client.getAddress(), client.getBankAccounts().size(), calculateTotalBalance(client.getBankAccounts()))).collect(Collectors.toList());
    }

    private Page<Client> findBySearchValue(String searchValue, Pageable pageable) {
        return clientDao.findByCinContainingOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrAddressContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue, pageable);
    }

    public long getTotalClientCount() {
        return clientDao.count();
    }

    public long getFilteredClientCount(String searchValue) {
        return clientDao.countByCinContainingOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrAddressContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue);
    }

    private float calculateTotalBalance(List<BankAccount> bankAccounts) {
        return (float) bankAccounts.stream().mapToDouble(BankAccount::getBalance).sum();
    }

    public List<ClientDto> searchClientAutoComplete(String searchValue) {
        List<Client> clients = clientDao.findByCinContainingOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrAddressContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue);
        return convertClientsToDTOs(clients);
    }
}
