package tn.iit.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Getter
@Setter
@NoArgsConstructor // obligatoire selon JEE
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity // cet objet fera un mapping avec la base de données
@Table(name = "t_client")
public class Client implements Serializable /* obligatoire selon JEE */ {
	private static final long serialVersionUID = 1L;
	@Include
	@Id // PK
	@Column(length = 10)
	private String cin;
	private String nom;
	private String prenom;

	// bidirectionnelle
	@Exclude // casser la boucle toString
	@JsonIgnore // casser la boucle Json
	@OneToMany(mappedBy = "client")
	// à elle seule --> table de jointure
	private List<Compte> comptes;
	//default fetch LAZY

	public Client(String cin, String nom, String prenom) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	

}
