package com.miage.umlconvertisseur;

import java.util.List;

public class CategorieUnite {

    public String nameCategorieUnite;
    public List<Unit> listUnit;

    public CategorieUnite() {
        super();
    }

    public CategorieUnite(String name, List<Unit> lstUnite) {
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

    public List<Unit> getLstUnite() {
        return listUnit;
    }

    public void setLstUnite(List<Unit> lstUnite) {
        this.listUnit = lstUnite;
    }

}
