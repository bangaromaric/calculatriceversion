package ga.banga.calculatriceversion.request;

import jakarta.validation.constraints.NotNull;

public class OperationAvanceeRequest {

    @NotNull(message = "Le nombre ne peut pas être nul")
    private Double number;

    private Double exposant;

    public OperationAvanceeRequest() {
    }

    public OperationAvanceeRequest(Double number) {
        this.number = number;
    }

    public OperationAvanceeRequest(Double number, Double exposant) {
        this.number = number;
        this.exposant = exposant;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getExposant() {
        return exposant;
    }

    public void setExposant(Double exposant) {
        this.exposant = exposant;
    }
}
