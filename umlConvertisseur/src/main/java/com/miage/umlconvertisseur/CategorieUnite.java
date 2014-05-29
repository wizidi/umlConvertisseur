package com.miage.umlconvertisseur;

import java.util.List;

public class CategorieUnite {

    public String nameCategorieUnite;
    public List<Unite> listUnit;

    public CategorieUnite() {
        super();
    }

    public CategorieUnite(String name, List<Unite> lstUnite) {
        super();
        this.nameCategorieUnite = name;
        this.listUnit = lstUnite;
    }

    public CategorieUnite(String nameCategorieUnite) {
        super();
        this.nameCategorieUnite = nameCategorieUnite;
    }

    public String getNameCategorieUnite() {
        return nameCategorieUnite;
    }

    public void setNameCategorieUnite(String nameCategorieUnite) {
        this.nameCategorieUnite = nameCategorieUnite;
    }

    public List<Unite> getLstUnite() {
        return listUnit;
    }

    public void setLstUnite(List<Unite> lstUnite) {
        this.listUnit = lstUnite;
    }

}
