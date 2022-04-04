package com.company;


import static com.company.Tools.randomRPId;

public class RemboursementPersonnel {
	private String Id=randomRPId();
	private Prets prets;
	private Student etudient;
	private double montant;
	
	public RemboursementPersonnel() {
	
	}

	public RemboursementPersonnel(String id, Prets prets, Student etudient, double montant) {
		super();
		Id = id;
		this.prets = prets;
		this.etudient = etudient;
		this.montant = montant;
	}

	public String getId() {
		return Id;
	}

	public Prets getPrets() {
		return prets;
	}

	public void setPrets(Prets prets) {
		this.prets = prets;
	}

	public Student getEtudient() {
		return etudient;
	}

	public void setEtudient(Student etudient) {
		this.etudient = etudient;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	@Override
	public String toString() {
		return "RemboursementPersonnel {" +
				"\n\tId=" + Id +
				"\n\t prets=" + prets +
				"\n\t etudient Id=" + etudient.getId_student() +
				"\n\t etudient nom et prenom=" + etudient.getF_name() +etudient.getL_name()+
				"\n\t montant=" + montant +
				'}';
	}
}
