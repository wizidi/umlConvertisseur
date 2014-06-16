package com.miage.umlconvertisseur;

import java.math.BigDecimal;

/**
 * This class contain an attribut (name) and two values (BigDecimal)
 * 
 * @author Antoine
 * @version 1.0
 */
public class Unit {

    // Nom de l'unite, par exemple cm
    public String nameUnit;
    // coefficient de multiplication de l'unite, par exemple 1,30
    public BigDecimal multiplication;
    // Decallage a realiser (addition), par exemple 30
    public BigDecimal addition;

    public Unit() {
        super();
    }

    public Unit(String nameUnite, BigDecimal multiParRapportDefaut,
            BigDecimal additionParRapportDefaut) {
        super();
        this.nameUnit = nameUnite;
        this.multiplication = multiParRapportDefaut;
        this.addition = additionParRapportDefaut;
    }

    /**
     *
     * @return current name of the unit
     */
    public String getNameUnite() {
        return nameUnit;
    }

    /**
     * Sets the name of the Unit
     *
     * @param nomUnite name of unit
     */
    public void setNomUnite(String nomUnite) {
        this.nameUnit = nomUnite;
    }

    /**
     *
     * @return current value of coef multiplication
     */
    public BigDecimal getmultiParRapportDefaut() {
        return multiplication;
    }

    /**
     * Sets the value of the coef multiplication
     *
     * @param multiParRapportDefaut value of coef multiplication
     */
    public void setmultiParRapportDefaut(BigDecimal multiParRapportDefaut) {
        this.multiplication = multiParRapportDefaut;
    }

    /**
     * 
     * @return current value of addition 
     */
    public BigDecimal getadditionParRapportDefaut() {
        return addition;
    }

    /**
     * Sets the value of the addition
     * 
     * @param additionParRapportDefaut value of addition
     */
    public void setadditionParRapportDefaut(BigDecimal additionParRapportDefaut) {
        this.addition = additionParRapportDefaut;
    }

}
