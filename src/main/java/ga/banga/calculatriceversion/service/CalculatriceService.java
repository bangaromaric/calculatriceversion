package ga.banga.calculatriceversion.service;

import ga.banga.calculatriceversion.exception.DivisionParZeroException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatriceService {
    public double addition(double number1, double number2) {
        return number1 + number2;
    }

    public double soustraction(double number1, double number2) {
        return number1 - number2;
    }

    public double multiplication(double number1, double number2) {
        return number1 * number2;
    }

    // Correction de la méthode division pour améliorer la précision
    public double division(double number1, double number2) {
        if (Math.abs(number2) < 1e-10) {
            throw new DivisionParZeroException("Division par zéro impossible");
        }

        // Utilisation de BigDecimal pour une meilleure précision
        BigDecimal dividend = BigDecimal.valueOf(number1);
        BigDecimal divisor = BigDecimal.valueOf(number2);

        // Effectuer la division avec une précision de 10 chiffres après la virgule
        return dividend.divide(divisor, 10, RoundingMode.HALF_UP).doubleValue();
    }
}
