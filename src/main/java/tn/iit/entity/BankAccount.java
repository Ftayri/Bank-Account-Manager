package tn.iit.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // obligatoire selon JEE
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity // cet objet fera un mapping avec la base de données
@Table(name = "accounts")
public class BankAccount implements Serializable /* obligatoire selon JEE */ {
	private static final long serialVersionUID = 1L;
	@Include
	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private Integer rib;
	private float solde;
	//unidirectionnelle
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;

	public BankAccount(float solde, Client client) {
		super();
		this.solde = solde;
		this.client = client;
	}
	//EAGER: lorsque je charge le compte, le client sera chargé avec
	//LAZY: lorsque je charge le compte, le client ne sera pas chargé
	// Il sera charge sur demande de la méthode getClient()
	// Default fetch
	// 1 --> EAGER
	// * --> LAZY
}
