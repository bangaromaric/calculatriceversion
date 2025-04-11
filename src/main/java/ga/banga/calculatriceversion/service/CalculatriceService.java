package ga.banga.calculatriceversion.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatriceService {
    public double addition(double number1, double number2) {
        return number1 + number2;
    }

    public double soustraction(double number1, double number2) {
        return number1 - number2;
    }
}
