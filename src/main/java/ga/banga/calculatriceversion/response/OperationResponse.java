package ga.banga.calculatriceversion.response;

public class OperationResponse {
    private Double result;
    private String operation;

    public OperationResponse() {
    }

    public OperationResponse(Double result, String operation) {
        this.result = result;
        this.operation = operation;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
