package ga.banga.calculatriceversion;

import ga.banga.calculatriceversion.request.OperationRequest;
import ga.banga.calculatriceversion.response.OperationResponse;
import ga.banga.calculatriceversion.service.CalculatriceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calculatrice")
public class CalculatriceController {

    private final CalculatriceService calculatriceService;

    public CalculatriceController(CalculatriceService calculatriceService) {
        this.calculatriceService = calculatriceService;
    }

    @PostMapping("/addition")
    public ResponseEntity<OperationResponse> addition(@Valid @RequestBody OperationRequest request) {
        double result = calculatriceService.addition(request.getNumber1(), request.getNumber2());
        return ResponseEntity.ok(new OperationResponse(result, "addition"));
    }

    @PostMapping("/soustraction")
    public ResponseEntity<OperationResponse> soustraction(@Valid @RequestBody OperationRequest request) {
        double result = calculatriceService.soustraction(request.getNumber1(), request.getNumber2());
        return ResponseEntity.ok(new OperationResponse(result, "soustraction"));
    }

    @PostMapping("/multiplication")
    public ResponseEntity<OperationResponse> multiplication(@Valid @RequestBody OperationRequest request) {
        double result = calculatriceService.multiplication(request.getNumber1(), request.getNumber2());
        return ResponseEntity.ok(new OperationResponse(result, "multiplication"));
    }

    @PostMapping("/division")
    public ResponseEntity<OperationResponse> division(@Valid @RequestBody OperationRequest request) {
        double result = calculatriceService.division(request.getNumber1(), request.getNumber2());
        return ResponseEntity.ok(new OperationResponse(result, "division"));
    }
}
