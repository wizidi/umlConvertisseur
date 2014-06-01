package com.miage.umlconvertisseur;

import java.math.BigDecimal;

public class Unite {

    // Nom de l'unit�, par exemple cm
    public String nameUnite;
    // multi de multiplication de l'unit�, par exemple 1,30
    public BigDecimal multiplication;
    // D�callage � r�aliser (addition), par exemple 30
    public BigDecimal addition;

    public Unite() {
        super();
    }

    public Unite(String nameUnite, BigDecimal multiParRapportDefaut,
                 BigDecimal additionParRapportDefaut) {
        super();
        this.nameUnite = nameUnite;
        this.multiplication = multiParRapportDefaut;
        this.addition = additionParRapportDefaut;
    }

    public String getNameUnite() {
        return nameUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nameUnite = nomUnite;
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
