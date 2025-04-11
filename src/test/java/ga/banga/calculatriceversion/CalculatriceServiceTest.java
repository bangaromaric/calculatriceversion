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

    @Test
    public void testAdditionGrandsNombres() {
        // Test avec des grands nombres
        double number1 = 1_000_000_000_000.0;
        double number2 = 1_000_000_000_000.0;
        assertEquals(2_000_000_000_000.0, calculatriceService.addition(number1, number2), 0.001);

        // Test avec des grands nombres et précision
        number1 = 1_000_000_000_000.123;
        number2 = 1_000_000_000_000.456;
        assertEquals(2_000_000_000_000.579, calculatriceService.addition(number1, number2), 0.001);
    }

    @Test
    public void testSoustractionGrandsNombres() {
        // Test avec des grands nombres
        double number1 = 1_000_000_000_000.0;
        double number2 = 999_999_999_999.0;
        assertEquals(1.0, calculatriceService.soustraction(number1, number2), 0.001);

        // Test avec des grands nombres et précision
        number1 = 1_000_000_000_000.123;
        number2 = 1_000_000_000_000.122;
        assertEquals(0.001, calculatriceService.soustraction(number1, number2), 0.000001);
    }

    @Test
    public void testMultiplicationGrandsNombres() {
        // Test avec des grands nombres
        double number1 = 1_000_000_000.0;
        double number2 = 1_000_000_000.0;
        assertEquals(1_000_000_000_000_000_000.0, calculatriceService.multiplication(number1, number2), 0.001);
    }

    @Test
    public void testDivisionGrandsNombres() {
        // Test avec des grands nombres
        double number1 = 1_000_000_000_000.0;
        double number2 = 1_000_000_000.0;
        assertEquals(1_000.0, calculatriceService.division(number1, number2), 0.001);

        // Test avec des petits nombres issus de grands nombres
        number1 = 1.0;
        number2 = 1_000_000_000.0;
        assertEquals(0.000000001, calculatriceService.division(number1, number2), 0.000000000001);
    }
}
