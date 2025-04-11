package ga.banga.calculatriceversion;

import ga.banga.calculatriceversion.exception.OperationImpossibleException;
import ga.banga.calculatriceversion.service.CalculatriceService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

//        // Test avec zéro
//        assertEquals(0.0, calculatriceService.racineCarree(0.0), 0.001);

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
    public void testSinus() {
        // Test basique - sin(0) = 0
        assertEquals(0.0, calculatriceService.sinus(0.0), 0.001);

        // Test avec π/2 - sin(π/2) = 1
        assertEquals(1.0, calculatriceService.sinus(Math.PI/2), 0.001);

        // Test avec π - sin(π) = 0
        assertEquals(0.0, calculatriceService.sinus(Math.PI), 0.001);
    }

    @Test
    public void testCosinus() {
        // Test basique - cos(0) = 1
        assertEquals(1.0, calculatriceService.cosinus(0.0), 0.001);

        // Test avec π/2 - cos(π/2) = 0
        assertEquals(0.0, calculatriceService.cosinus(Math.PI/2), 0.001);

        // Test avec π - cos(π) = -1
        assertEquals(-1.0, calculatriceService.cosinus(Math.PI), 0.001);
    }

    @Test
    public void testTangente() {
        // Test basique - tan(0) = 0
        assertEquals(0.0, calculatriceService.tangente(0.0), 0.001);

        // Test avec π/4 - tan(π/4) = 1
        assertEquals(1.0, calculatriceService.tangente(Math.PI/4), 0.001);

        // Test exception avec π/2
        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.tangente(Math.PI/2);
        });
    }

    @Test
    public void testArrondir() {
        // Test avec 2 décimales
        assertEquals(3.14, calculatriceService.arrondir(3.14159, 2), 0.0001);

        // Test avec 0 décimale (arrondi à l'entier)
        assertEquals(3.0, calculatriceService.arrondir(3.14159, 0), 0.0001);

        // Test avec 4 décimales
        assertEquals(3.1416, calculatriceService.arrondir(3.14159, 4), 0.00001);

        // Test avec nombre négatif
        assertEquals(-3.14, calculatriceService.arrondir(-3.14159, 2), 0.0001);

        // Test exception avec nombre négatif de décimales
        assertThrows(IllegalArgumentException.class, () -> {
            calculatriceService.arrondir(3.14159, -1);
        });
    }

    @Test
    public void testRacineCarreeAmelioree() {
        // Test avec un très grand nombre
        assertEquals(100000.0, calculatriceService.racineCarree(10000000000.0), 0.001);

        // Test avec un très petit nombre
        assertEquals(0.0001, calculatriceService.racineCarree(0.00000001), 0.00000001);
    }

    @Test
    public void testExponentielle() {
        // Test basique
        assertEquals(Math.E, calculatriceService.exponentielle(1.0), 0.001); // e^1 = e

        // Test avec 0
        assertEquals(1.0, calculatriceService.exponentielle(0.0), 0.001); // e^0 = 1

        // Test avec valeur négative
        assertEquals(0.0067, calculatriceService.exponentielle(-5.0), 0.001); // e^-5 ≈ 0.0067

        // Test valeur limite - devrait retourner une valeur très grande
        double result = calculatriceService.exponentielle(10.0); // e^10 ≈ 22026.47
        assertTrue(result > 22000.0 && result < 22100.0);

        // Test exception avec valeur trop grande
        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.exponentielle(750.0);
        });
    }

    @Test
    public void testLogarithmeNaturel() {
        // Test basique
        assertEquals(1.0, calculatriceService.logarithmeNaturel(Math.E), 0.001); // ln(e) = 1

        // Test avec 1
        assertEquals(0.0, calculatriceService.logarithmeNaturel(1.0), 0.001); // ln(1) = 0

        // Test avec valeur positive
        assertEquals(2.303, calculatriceService.logarithmeNaturel(10.0), 0.001); // ln(10) ≈ 2.303

        // Test exception avec valeur négative
        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.logarithmeNaturel(-1.0);
        });

        // Test exception avec valeur zéro
        assertThrows(OperationImpossibleException.class, () -> {
            calculatriceService.logarithmeNaturel(0.0);
        });
    }
}
