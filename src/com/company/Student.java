package com.company;

import static com.company.Tools.randomSId;

public class Student {
	private String id_student=randomSId();
	private String f_name;
	private String l_name;
	private Niveau niveau;
	private String gendr;
	private String phone;
	private String adresse;
	private String piece;
	private long piece_numbr;
	
	
	// construc null
	public Student() {
		super();
		
	}

		//constructr with param

	public Student( String f_name, String l_name,String gendr, String phone,String adresse, String piece,
			long piece_numbr) {
		super();
		this.f_name = f_name;
		this.l_name = l_name;
		this.gendr = gendr;
		this.phone = phone;
		this.adresse=adresse;
		this.piece = piece;
		this.piece_numbr = piece_numbr;
	}



	public String getId_student() {
		return id_student;
	}

	public String getF_name() {
		return f_name;
	}



	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}



	public String getL_name() {
		return l_name;
	}



	public void setL_name(String l_name) {
		this.l_name = l_name;
	}



	public String getGendr() {
		return gendr;
	}



	public void setGendr(String gendr) {
		this.gendr = gendr;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public String getAdresse(String adresse) {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getPiece() {
		return piece;
	}



	public void setPiece(String piece) {
		this.piece = piece;
	}



	public long getPiece_numbr() {
		return piece_numbr;
	}



	public void setPiece_numbr(long piece_numbr) {
		this.piece_numbr = piece_numbr;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id_student='" + id_student + '\'' +
				"\n\t f_name='" + f_name + '\'' +
				"\n\t l_name='" + l_name + '\'' +
				"\n\t niveau=" + niveau +
				"\n\t gendr='" + gendr + '\'' +
				"\n\t phone='" + phone + '\'' +
				"\n\t adresse='" + adresse + '\'' +
				"\n\t piece='" + piece + '\'' +
				"\n\t piece_numbr=" + piece_numbr +
				'}';
	}
}
