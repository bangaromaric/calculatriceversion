package ga.banga.calculatriceversion.service;

import ga.banga.calculatriceversion.exception.DivisionParZeroException;
import ga.banga.calculatriceversion.exception.OperationImpossibleException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class CalculatriceService {

    // Précision commune pour tous les calculs
    private static final MathContext MATH_CONTEXT = new MathContext(15, RoundingMode.HALF_UP);

    // Méthode addition corrigée pour grands nombres
    public double addition(double number1, double number2) {
        // Utiliser BigDecimal pour éviter les problèmes de précision avec les grands nombres
        BigDecimal bd1 = BigDecimal.valueOf(number1);
        BigDecimal bd2 = BigDecimal.valueOf(number2);
        return bd1.add(bd2, MATH_CONTEXT).doubleValue();
    }

    // Méthode soustraction corrigée pour grands nombres
    public double soustraction(double number1, double number2) {
        BigDecimal bd1 = BigDecimal.valueOf(number1);
        BigDecimal bd2 = BigDecimal.valueOf(number2);
        return bd1.subtract(bd2, MATH_CONTEXT).doubleValue();
    }

    // Méthode multiplication corrigée pour grands nombres
    public double multiplication(double number1, double number2) {
        BigDecimal bd1 = BigDecimal.valueOf(number1);
        BigDecimal bd2 = BigDecimal.valueOf(number2);
        return bd1.multiply(bd2, MATH_CONTEXT).doubleValue();
    }

    // Méthode division déjà bien implémentée pour la précision
    public double division(double number1, double number2) {
        if (Math.abs(number2) < 1e-10) {
            throw new DivisionParZeroException("Division par zéro impossible");
        }

        BigDecimal dividend = BigDecimal.valueOf(number1);
        BigDecimal divisor = BigDecimal.valueOf(number2);

        return dividend.divide(divisor, 10, RoundingMode.HALF_UP).doubleValue();
    }

    // Méthodes avancées (si implémentées) restent inchangées car elles utilisent Math.sqrt et Math.pow
    // qui ont déjà une bonne précision

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
