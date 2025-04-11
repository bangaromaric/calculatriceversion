package ga.banga.calculatriceversion.request;

import jakarta.validation.constraints.NotNull;

public class OperationAngleRequest {

    @NotNull(message = "L'angle ne peut pas être nul")
    private Double angleRadians;

    private Boolean degres;

    public OperationAngleRequest() {
    }

    public OperationAngleRequest(Double angleRadians) {
        this.angleRadians = angleRadians;
        this.degres = false;
    }

    public OperationAngleRequest(Double angleRadians, Boolean degres) {
        this.angleRadians = angleRadians;
        this.degres = degres;
    }

    public Double getAngleRadians() {
        return angleRadians;
    }

    public void setAngleRadians(Double angleRadians) {
        this.angleRadians = angleRadians;
    }

    public Boolean getDegres() {
        return degres != null ? degres : false;
    }

    public void setDegres(Boolean degres) {
        this.degres = degres;
    }
}
