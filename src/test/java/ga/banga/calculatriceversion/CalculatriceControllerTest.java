package ga.banga.calculatriceversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.banga.calculatriceversion.request.OperationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
}
