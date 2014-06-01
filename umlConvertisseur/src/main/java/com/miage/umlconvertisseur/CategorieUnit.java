package com.miage.umlconvertisseur;

import java.util.List;

public class CategorieUnit {

    public String nameCategorieUnite;
    public List<Unit> listUnit;

    public CategorieUnit() {
        super();
    }

    public CategorieUnit(String name, List<Unit> lstUnite) {
        super();
        this.nameCategorieUnite = name;
        this.listUnit = lstUnite;
    }

    public CategorieUnit(String nameCategorieUnite) {
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
