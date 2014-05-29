package com.miage.umlconvertisseur;

import java.util.List;

public class Grandeur {

    public String nameMesure;
    public List<Unite> listUnit;

    public Grandeur() {
        super();
    }

    public Grandeur(String nomMesure, List<Unite> lstUnite) {
        super();
        this.nameMesure = nomMesure;
        this.listUnit = lstUnite;
    }

    public Grandeur(String nomMesure) {
        super();
        this.nameMesure = nomMesure;
    }

    public String getNomMesure() {
        return nameMesure;
    }

    public void setNomMesure(String nomMesure) {
        this.nameMesure = nomMesure;
    }

    public List<Unite> getLstUnite() {
        return listUnit;
    }

    public void setLstUnite(List<Unite> lstUnite) {
        this.listUnit = lstUnite;
    }

}
