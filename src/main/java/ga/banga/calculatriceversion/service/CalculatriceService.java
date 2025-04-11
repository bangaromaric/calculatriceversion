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

    // Méthode racineCarree améliorée pour plus de précision
    public double racineCarree(double number) {
        if (number < 0) {
            throw new OperationImpossibleException("Impossible de calculer la racine carrée d'un nombre négatif");
        }

        // Pour les grands nombres ou très petits nombres, utiliser une approche avec BigDecimal
        if (number > 1e10 || number < 1e-10) {
            BigDecimal bd = BigDecimal.valueOf(number);
            // Newton-Raphson method for square root approximation with BigDecimal
            BigDecimal x = bd.divide(BigDecimal.valueOf(2), MATH_CONTEXT);
            BigDecimal previous;

            do {
                previous = x;
                x = x.add(bd.divide(x, MATH_CONTEXT)).divide(BigDecimal.valueOf(2), MATH_CONTEXT);
            } while (x.subtract(previous).abs().compareTo(BigDecimal.valueOf(1e-15)) > 0);

            return x.doubleValue();
        }

        // Pour les nombres normaux, utiliser Math.sqrt qui est optimisé
        return Math.sqrt(number);
    }
    // Nouvelle méthode pour calculer exponentielle (e^x)
    public double exponentielle(double exposant) {
        // Pour les exposants extrêmes, détecter les dépassements potentiels
        if (exposant > 709) { // Approximation de ln(Double.MAX_VALUE)
            throw new OperationImpossibleException("Exposant trop grand, résultat supérieur à la valeur maximale représentable");
        }

        if (exposant < -745) { // Approximation de ln(Double.MIN_VALUE)
            return 0.0; // Underflow, retourne 0 comme approximation
        }

        return Math.exp(exposant);
    }
    // Correction de la méthode logarithmeNaturel
    public double logarithmeNaturel(double number) {
        if (number <= 0) {
            throw new OperationImpossibleException("Impossible de calculer le logarithme naturel d'un nombre négatif ou nul");
        }

        // Traitement spécial pour les nombres très proches de zéro
        if (number < 1e-10) {
            throw new OperationImpossibleException("Valeur trop proche de zéro, risque d'imprécision numérique");
        }

        return Math.log(number);
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

    // Nouvelles méthodes trigonométriques

    /**
     * Calcule le sinus de l'angle en radians
     * @param angleRadians angle en radians
     * @return sinus de l'angle
     */
    public double sinus(double angleRadians) {
        return Math.sin(angleRadians);
    }

    /**
     * Calcule le cosinus de l'angle en radians
     * @param angleRadians angle en radians
     * @return cosinus de l'angle
     */
    public double cosinus(double angleRadians) {
        return Math.cos(angleRadians);
    }

    /**
     * Calcule la tangente de l'angle en radians
     * @param angleRadians angle en radians
     * @return tangente de l'angle
     */
    public double tangente(double angleRadians) {
        // Normalisation de l'angle dans [0, 2π[
        double normalizedAngle = angleRadians % (2 * Math.PI);
        if (normalizedAngle < 0) {
            normalizedAngle += 2 * Math.PI;
        }

        // Vérification plus précise des cas où tangente n'est pas définie
        double epsilon = 1e-12;
        double halfPi = Math.PI / 2;

        for (int k = 0; k < 4; k++) {
            double singularPoint = halfPi + k * Math.PI;
            if (Math.abs(normalizedAngle - singularPoint) < epsilon) {
                throw new OperationImpossibleException(
                        String.format("La tangente n'est pas définie pour cet angle (%s)",
                                k % 2 == 0 ? "π/2 + 2kπ" : "3π/2 + 2kπ")
                );
            }
        }

        // Utilisation de sin/cos pour les cas proches des limites
        if (Math.abs(Math.cos(angleRadians)) < 0.001) {
            double sin = Math.sin(angleRadians);
            double cos = Math.cos(angleRadians);
            return sin / cos;
        }

        return Math.tan(angleRadians);
    }

    /**
     * Arrondit un résultat avec le nombre de décimales spécifié
     * @param value valeur à arrondir
     * @param decimalPlaces nombre de décimales à conserver
     * @return valeur arrondie
     */
    public double arrondir(double value, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("Le nombre de décimales ne peut pas être négatif");
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }



}
