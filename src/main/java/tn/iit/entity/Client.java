package tn.iit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import lombok.ToString.Exclude;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor // obligatoire selon JEE
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity // cet objet fera un mapping avec la base de données
@Table(name = "clients")
public class Client implements Serializable /* obligatoire selon JEE */ {
    private static final long serialVersionUID = 1L;
    @Include
    @Id
    @Column(length = 10, nullable = false, unique = true)
    @Size(min = 10, max = 10, message = "CIN must be exactly 10 characters")
    private String cin;

    @NotEmpty(message = "First Name cannot be empty.")
    private String firstName;
    @NotEmpty(message = "Last Name cannot be empty.")
    private String lastName;
    @NotEmpty(message = "Address cannot be empty.")
    private String address;

    // bidirectionnelle
    @Exclude // casser la boucle toString
    @JsonIgnore // casser la boucle Json
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    // à elle seule --> table de jointure
    private List<BankAccount> bankAccounts;
    //default fetch LAZY

    public Client(String cin, String firstName, String lastName, String address) {
        super();
        this.cin = cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }


}
