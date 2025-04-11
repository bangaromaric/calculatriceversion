package ga.banga.calculatriceversion.service;

import ga.banga.calculatriceversion.exception.DivisionParZeroException;
import ga.banga.calculatriceversion.exception.OperationImpossibleException;
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

    public double racineCarree(double number) {
        if (number < 0) {
            throw new OperationImpossibleException("Impossible de calculer la racine carrée d'un nombre négatif");
        }
        return Math.sqrt(number);
    }

    public double puissance(double base, double exposant) {
        // Pour gérer les cas particuliers comme 0^0 qui est indéterminé mathématiquement
        if (Math.abs(base) < 1e-10 && Math.abs(exposant) < 1e-10) {
            throw new OperationImpossibleException("L'opération 0^0 est indéterminée");
        }

        // Cas spécial: base négative avec exposant non entier
        if (base < 0 && Math.floor(exposant) != exposant) {
            throw new OperationImpossibleException("Impossible d'élever un nombre négatif à une puissance non entière");
        }

        return Math.pow(base, exposant);
    }
}
