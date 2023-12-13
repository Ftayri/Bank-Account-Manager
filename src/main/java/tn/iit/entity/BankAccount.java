package tn.iit.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "accounts")
public class BankAccount implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rib;

    private float balance;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public BankAccount(float balance, Client client) {
        super();
        this.balance = balance;
        this.client = client;
    }
}
