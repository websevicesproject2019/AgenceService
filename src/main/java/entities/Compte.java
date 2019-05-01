package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Compte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(length=10)
	private String numCpt;
	private String libelle;
	@Enumerated(EnumType.STRING)
	private Sens sens;
	private double solde;
	private double decouvert;
	@ManyToOne
	@JoinColumn(name="numCli")
	private Client numCli;
	

	public Compte() {
		super();
		this.solde=0;
		this.sens = Sens.CR;
	}

	public Compte(String numCpt, String libelle,Sens sens,double solde, Client numCli,double decouvert) {
		super();
		this.numCpt = numCpt;
		this.libelle = libelle;
		this.sens = sens ;
		this.solde = solde;
		this.numCli = numCli;
		this.decouvert = decouvert;
	}

	public String getNumCpt() {
		return numCpt;
	}

	public void setNumCpt(String numCpt) {
		this.numCpt = numCpt;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	//@Enumerated(EnumType.STRING)
	public Sens getSens() {
		return sens;
	}

	public void setSens(Sens sens) {
		this.sens = sens;
	}

	public double getSolde() {
		return solde;
	}
	
	public void setSolde(double solde) {
		this.solde = solde; 
	}

	public Client getNumCli() {
		return numCli;
	}

	public void setNumCli(Client numCli) {
		this.numCli = numCli;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
	public void debit(double montant) throws SoldeException {
		if(this.solde + this.decouvert >= montant) {
			this.solde -= montant;
			System.out.println("Account successfully debited");
			if(montant + decouvert <= 0 ) 
				this.sens = Sens.CR;
			if(montant > 0) 
				this.sens = Sens.DB;
				
		}
		else throw new SoldeException("You can't debite this montant");
	}
	
	public void credit(double montant) {
		this.solde += montant;
		System.out.println("Account successfully credited");
		if(montant > 0)
			this.sens = Sens.DB;
	}
}

