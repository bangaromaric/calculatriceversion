package ga.banga.calculatriceversion;

import ga.banga.calculatriceversion.service.CalculatriceService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatriceServiceTest {

    private final CalculatriceService calculatriceService = new CalculatriceService();

    @Test
    public void testAddition() {
        // Test avec des nombres positifs
        assertEquals(5.0, calculatriceService.addition(2.0, 3.0), 0.001);

        // Test avec un nombre négatif
        assertEquals(-1.0, calculatriceService.addition(2.0, -3.0), 0.001);

        // Test avec des nombres décimaux
        assertEquals(5.3, calculatriceService.addition(2.1, 3.2), 0.001);
    }

    @Test
    public void testSoustraction() {
        // Test avec des nombres positifs
        assertEquals(2.0, calculatriceService.soustraction(5.0, 3.0), 0.001);

        // Test avec résultat négatif
        assertEquals(-1.0, calculatriceService.soustraction(2.0, 3.0), 0.001);

        // Test avec des nombres décimaux
        assertEquals(1.1, calculatriceService.soustraction(3.3, 2.2), 0.001);
    }
}
