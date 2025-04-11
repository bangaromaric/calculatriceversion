package ga.banga.calculatriceversion.request;

import jakarta.validation.constraints.NotNull;

public class ArrondiRequest {
    @NotNull(message = "La valeur ne peut pas être nulle")
    private Double valeur;

    @NotNull(message = "Le nombre de décimales ne peut pas être nul")
    private Integer decimales;

    public ArrondiRequest() {
    }

    public ArrondiRequest(Double valeur, Integer decimales) {
        this.valeur = valeur;
        this.decimales = decimales;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Integer getDecimales() {
        return decimales;
    }

    public void setDecimales(Integer decimales) {
        this.decimales = decimales;
    }
}
