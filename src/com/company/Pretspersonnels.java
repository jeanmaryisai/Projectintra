package com.company;

import static com.company.Dao.typeText;
import static com.company.Tools.randomPPId;

public class Pretspersonnels {
    private String id=randomPPId();
    private Student student;
    private double montant;
    private Prets pret;

    public String getId() {
        return id;
    }

    public Prets getPret() {
        return pret;
    }

    public void setPret(Prets pret) {
        this.pret = pret;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String stringgson(){
        return id+typeText.delimiteur+student.getId_student()+typeText.delimiteur+montant+typeText.delimiteur
                + pret.getId_prets();
    }
    @Override
    public String toString() {
        return "Pretspersonnels{" +
                "\n\tid='" + id + '\'' +
                "\n\t student=" + student.getF_name() +" "+student.getL_name()+
                "\n\t montant=" + montant +
                '}';
    }
}
