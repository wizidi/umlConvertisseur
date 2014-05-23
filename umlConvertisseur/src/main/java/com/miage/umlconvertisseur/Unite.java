package com.miage.umlconvertisseur;

import java.math.BigDecimal;

public class Unite {

    // Nom de l'unit�, par exemple cm
    public String nomUnite;
    // multi de multiplication de l'unit�, par exemple 1,30
    public BigDecimal multiParRapportDefaut;
    // D�callage � r�aliser (addition), par exemple 30
    public BigDecimal additionParRapportDefaut;

    public Unite() {
        super();
    }

    public Unite(String nomUnite, BigDecimal multiParRapportDefaut,
                 BigDecimal additionParRapportDefaut) {
        super();
        this.nomUnite = nomUnite;
        this.multiParRapportDefaut = multiParRapportDefaut;
        this.additionParRapportDefaut = additionParRapportDefaut;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    public BigDecimal getmultiParRapportDefaut() {
        return multiParRapportDefaut;
    }

    public void setmultiParRapportDefaut(BigDecimal multiParRapportDefaut) {
        this.multiParRapportDefaut = multiParRapportDefaut;
    }

    public BigDecimal getadditionParRapportDefaut() {
        return additionParRapportDefaut;
    }

    public void setadditionParRapportDefaut(BigDecimal additionParRapportDefaut) {
        this.additionParRapportDefaut = additionParRapportDefaut;
    }

}
