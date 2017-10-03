package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
@SuppressWarnings("serial")
@Entity
public class Candidat implements Serializable{
	@Id @GeneratedValue
	private Long id;
	
	private String nom;
	
	private String prenom;
	
	private String adresse;
	
	private String telephone;

	@Email
	private String email;
	

	private String fonction;

	private String situation;//menu déroulant

	private String dispo;//menu déroulant
	
	private String type;//menu déroulant
	
	private String salaire;
	
	private String cv;
	
	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getDispo() {
		return dispo;
	}

	public void setDispo(String dispo) {
		this.dispo = dispo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSalaire() {
		return salaire;
	}

	public void setSalaire(String salaire) {
		this.salaire = salaire;
	}
	
	public Candidat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Candidat(Long id, String nom, String prenom, String adresse, String telephone, String email,
			String situation, String cv) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.situation = situation;
		this.cv = cv;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}


	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}
	
}
