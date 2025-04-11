package ga.banga.calculatriceversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.banga.calculatriceversion.exception.DivisionParZeroException;
import ga.banga.calculatriceversion.request.OperationRequest;
import ga.banga.calculatriceversion.service.CalculatriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatriceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final CalculatriceService calculatriceService = new CalculatriceService();


    @Test
    public void testAddition() throws Exception {
        OperationRequest request = new OperationRequest(5.0, 3.0);

        mockMvc.perform(post("/api/v1/calculatrice/addition")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(8.0))
                .andExpect(jsonPath("$.operation").value("addition"));
    }

    @Test
    public void testSoustraction() throws Exception {
        OperationRequest request = new OperationRequest(5.0, 3.0);

        mockMvc.perform(post("/api/v1/calculatrice/soustraction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(2.0))
                .andExpect(jsonPath("$.operation").value("soustraction"));
    }

    @Test
    public void testValidation() throws Exception {
        OperationRequest request = new OperationRequest(null, 3.0);

        mockMvc.perform(post("/api/v1/calculatrice/addition")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testMultiplication() {
        // Test basique
        assertEquals(6.0, calculatriceService.multiplication(2.0, 3.0), 0.001);

        // Test avec un nombre négatif
        assertEquals(-6.0, calculatriceService.multiplication(2.0, -3.0), 0.001);

        // Test avec zéro
        assertEquals(0.0, calculatriceService.multiplication(5.0, 0.0), 0.001);

        // Test avec des nombres décimaux
        assertEquals(6.72, calculatriceService.multiplication(2.1, 3.2), 0.001);
    }

    @Test
    public void testDivision() {
        // Test basique
        assertEquals(2.0, calculatriceService.division(6.0, 3.0), 0.001);

        // Test avec un nombre négatif
        assertEquals(-2.0, calculatriceService.division(6.0, -3.0), 0.001);

        // Test avec des nombres décimaux
        assertEquals(2.5, calculatriceService.division(5.0, 2.0), 0.001);
    }

    @Test
    public void testDivisionParZero() {
        // Test de l'exception lors d'une division par zéro
        assertThrows(DivisionParZeroException.class, () -> {
            calculatriceService.division(5.0, 0.0);
        });

        // Test avec une valeur très proche de zéro
        assertThrows(DivisionParZeroException.class, () -> {
            calculatriceService.division(5.0, 1e-11);
        });
    }
}
