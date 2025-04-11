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

    @Test
    public void testLogarithme() {
        // Log base 10 de 100 = 2
        assertEquals(2.0, calculatriceService.logarithme(100.0, 10.0), 0.001);

        // Log base 2 de 8 = 3
        assertEquals(3.0, calculatriceService.logarithme(8.0, 2.0), 0.001);

        // Logarithme avec des valeurs décimales
        assertEquals(1.5, calculatriceService.logarithme(5.196, 2.3), 0.001);

        // Test avec nombre négatif
        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.logarithme(-10.0, 10.0);
        });

        // Test avec base invalide
        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.logarithme(10.0, 1.0);
        });

        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.logarithme(10.0, -2.0);
        });
    }

    @Test
    public void testLogarithmeNaturel() {
        // ln(e) = 1
        assertEquals(1.0, calculatriceService.logarithmeNaturel(Math.E), 0.001);

        // ln(1) = 0
        assertEquals(0.0, calculatriceService.logarithmeNaturel(1.0), 0.001);

        // Test avec nombre négatif
        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.logarithmeNaturel(-5.0);
        });
    }

    @Test
    public void testLogarithmeDecimal() {
        // log10(100) = 2
        assertEquals(2.0, calculatriceService.logarithmeDecimal(100.0), 0.001);

        // log10(1000) = 3
        assertEquals(3.0, calculatriceService.logarithmeDecimal(1000.0), 0.001);

        // log10(0.1) = -1
        assertEquals(-1.0, calculatriceService.logarithmeDecimal(0.1), 0.001);

        // Test avec nombre négatif
        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.logarithmeDecimal(-10.0);
        });
    }
}
