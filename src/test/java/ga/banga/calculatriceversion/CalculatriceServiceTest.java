package ga.banga.calculatriceversion;

import ga.banga.calculatriceversion.exception.OperationImpossibleException;
import ga.banga.calculatriceversion.service.CalculatriceService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void testRacineCarree() {
        // Test basique
        assertEquals(2.0, calculatriceService.racineCarree(4.0), 0.001);

        // Test avec nombre décimal
        assertEquals(1.5, calculatriceService.racineCarree(2.25), 0.001);

        // Test avec zéro
        assertEquals(0.0, calculatriceService.racineCarree(0.0), 0.001);

        // Test nombre négatif
        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.racineCarree(-4.0);
        });
    }

    @Test
    public void testPuissance() {
        // Test basique
        assertEquals(8.0, calculatriceService.puissance(2.0, 3.0), 0.001);

        // Test avec exposant négatif
        assertEquals(0.25, calculatriceService.puissance(2.0, -2.0), 0.001);

        // Test avec base 0
        assertEquals(0.0, calculatriceService.puissance(0.0, 5.0), 0.001);

        // Test avec exposant 0
        assertEquals(1.0, calculatriceService.puissance(7.0, 0.0), 0.001);

        // Test cas spécial 0^0
        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.puissance(0.0, 0.0);
        });

        // Test avec base négative et exposant décimal
        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.puissance(-2.0, 0.5);
        });
    }
}
