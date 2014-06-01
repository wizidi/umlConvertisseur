package com.miage.umlconvertisseur;

import java.math.BigDecimal;

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

    public String getNameUnite() {
        return nameUnit;
    }

    public void setNomUnite(String nomUnite) {
        this.nameUnit = nomUnite;
    }

    public BigDecimal getmultiParRapportDefaut() {
        return multiplication;
    }

    public void setmultiParRapportDefaut(BigDecimal multiParRapportDefaut) {
        this.multiplication = multiParRapportDefaut;
    }

    public BigDecimal getadditionParRapportDefaut() {
        return addition;
    }

    public void setadditionParRapportDefaut(BigDecimal additionParRapportDefaut) {
        this.addition = additionParRapportDefaut;
    }

}
