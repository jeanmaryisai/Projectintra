package com.company;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.company.Tools.randomRId;

public class Remboursement {
	private String id=randomRId();
	private Niveau niveau;
	private Prets idprets;
	private String nomVers;
	private Student student;
	private double montant;
	private Set<RemboursementPersonnel> remboursementPersonnels =new HashSet<RemboursementPersonnel>();
	private LocalDate date;

	public Set<RemboursementPersonnel> getRemboursementPersonnels() {
		return remboursementPersonnels;
	}

	public void setRemboursementPersonnels(Set<RemboursementPersonnel> remboursementPersonnels) {
		this.remboursementPersonnels = remboursementPersonnels;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<RemboursementPersonnel> getDtailRembos() {
		return remboursementPersonnels;
	}

	public void setDtailRembos(Set<RemboursementPersonnel> remboursementPersonnels) {
		this.remboursementPersonnels = remboursementPersonnels;
	}

	public double getMontant(){
		montant=0;
		for(RemboursementPersonnel x: remboursementPersonnels){
			montant += x.getMontant();
		}
		return montant;
	}
	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	private Prets montantVerse;
	private LocalDate dateRenboursement;
	Set<RemboursementPersonnel>listDtail= new HashSet<RemboursementPersonnel>();
	
	public Remboursement() {
	
	}
	
	
	




	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Prets getIdprets() {
		return idprets;
	}



	public void setIdprets(Prets idprets) {
		this.idprets = idprets;
	}



	public String getNomVers() {
		return nomVers;
	}



	public void setNomVers(String nomVers) {
		this.nomVers = nomVers;
	}



	public Student getStudent() {
		return student;
	}



	public void setStudent(Student student) {
		this.student = student;
	}



	public Prets getMontantVerse() {
		return montantVerse;
	}



	public void setMontantVerse(Prets montantVerse) {
		this.montantVerse = montantVerse;
	}



	public LocalDate getDateRenboursement() {
		return dateRenboursement;
	}



	public void
	setDateRenboursement(LocalDate dateRenboursement) {
		this.dateRenboursement = dateRenboursement;
	}



	public Set<RemboursementPersonnel> getListDtail() {
		return listDtail;
	}



	public void setListDtail(Set<RemboursementPersonnel> listDtail) {
		this.listDtail = listDtail;
	}

	@Override
	public String toString() {
		String xe="Remboursement{" +
				"id=" + id +
				"\n\t niveau=" + niveau.getNiveau() +
				"\n\t id du prets=" + idprets.getId_prets() +
				//"\n\t nomVers='" + nomVers + '\'' +
				//"\n\t student=" + student.getL_name() + " "+student.getF_name()+
				"\n\t montant=" + getMontant() +
				"\n\t List Etudiant ayant participer a ce remboursement:\n\t";
					for (RemboursementPersonnel x:remboursementPersonnels) {
							xe+=x.getEtudient().getF_name()+
								" "+x.getEtudient().getL_name()+" d'Id "+
								x.getEtudient().getId_student()+" a placer "
								+x.getMontant()+" gourdes\n\t";}
								xe+="\n\t montantVerse=" + montantVerse +
								"\n\t dateRenboursement=" + dateRenboursement.toString() +
								'}';
					return xe;
					}
	}

