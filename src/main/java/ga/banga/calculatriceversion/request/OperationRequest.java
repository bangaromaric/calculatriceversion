package ga.banga.calculatriceversion.request;

import jakarta.validation.constraints.NotNull;

public class OperationRequest {

    @NotNull(message = "Le premier nombre ne peut pas être nul")
    private Double number1;

    @NotNull(message = "Le deuxième nombre ne peut pas être nul")
    private Double number2;

    public OperationRequest() {
    }

    public OperationRequest(Double number1, Double number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public Double getNumber1() {
        return number1;
    }

    public void setNumber1(Double number1) {
        this.number1 = number1;
    }

    public Double getNumber2() {
        return number2;
    }

    public void setNumber2(Double number2) {
        this.number2 = number2;
    }

}
