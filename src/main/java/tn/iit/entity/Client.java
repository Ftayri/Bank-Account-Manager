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

@Entity
@Table(name = "clients")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Include
    @Id
    @Column(length = 8, nullable = false, unique = true)
    @Size(min = 8, max = 8, message = "CIN must be exactly 10 characters")
    private String cin;

    @NotEmpty(message = "First Name cannot be empty.")
    private String firstName;
    @NotEmpty(message = "Last Name cannot be empty.")
    private String lastName;
    @NotEmpty(message = "Address cannot be empty.")
    private String address;

    @Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts;

    public Client(String cin, String firstName, String lastName, String address) {
        super();
        this.cin = cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }


}
