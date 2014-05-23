package com.miage.umlconvertisseur;

import java.util.List;

public class Mesure {

    public String nomMesure;
    public List<Unite> lstUnite;

    public Mesure() {
        super();
    }

    public Mesure(String nomMesure, List<Unite> lstUnite) {
        super();
        this.nomMesure = nomMesure;
        this.lstUnite = lstUnite;
    }

    public Mesure(String nomMesure) {
        super();
        this.nomMesure = nomMesure;
    }

    public String getNomMesure() {
        return nomMesure;
    }

    public void setNomMesure(String nomMesure) {
        this.nomMesure = nomMesure;
    }

    public List<Unite> getLstUnite() {
        return lstUnite;
    }

    public void setLstUnite(List<Unite> lstUnite) {
        this.lstUnite = lstUnite;
    }

}
