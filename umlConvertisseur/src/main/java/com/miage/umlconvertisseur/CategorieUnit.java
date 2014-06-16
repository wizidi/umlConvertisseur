package com.miage.umlconvertisseur;

import java.util.List;

/**
 * This class contain an attribute (name) and a list of unit
 * 
 * @author Antoine and Alexandre
 * @version 1.0
 */
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

    /**
     * 
     * @param nameCategorieUnite name of the category
     */
    public CategorieUnit(String nameCategorieUnite) {
        super();
        this.nameCategorieUnite = nameCategorieUnite;
    }

    /**
     * 
     * @return current name of the category
     */
    public String getNameCategorieUnite() {
        return nameCategorieUnite;
    }

    /**
     * Sets the name of the category
     * 
     * @param nameCategorieUnite category to set (String)
     */
    public void setNameCategorieUnite(String nameCategorieUnite) {
        this.nameCategorieUnite = nameCategorieUnite;
    }

    /**
     * 
     * @return current list of unit
     */
    public List<Unit> getLstUnite() {
        return listUnit;
    }

    /**
     * Sets the list of Unit
     * 
     * @param lstUnite list of Unit to set 
     */
    public void setLstUnite(List<Unit> lstUnite) {
        this.listUnit = lstUnite;
    }

}
