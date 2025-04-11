package ga.banga.calculatriceversion.service;

import ga.banga.calculatriceversion.exception.DivisionParZeroException;
import org.springframework.stereotype.Service;

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
}
