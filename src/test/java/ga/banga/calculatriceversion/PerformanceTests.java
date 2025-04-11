package ga.banga.calculatriceversion;

import ga.banga.calculatriceversion.service.CalculatriceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class PerformanceTests {
    private final CalculatriceService calculatriceService = new CalculatriceService();

    @Test
    @DisplayName("Test de performance pour 1 million d'additions")
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    public void testPerformanceAddition() {
        IntStream.range(0, 1_000_000).forEach(i ->
                calculatriceService.addition(i, i + 1)
        );
    }

    @Test
    @DisplayName("Test de performance pour 100 000 calculs trigonométriques")
    @Timeout(value = 3, unit = TimeUnit.SECONDS)
    public void testPerformanceTrigonometrique() {
        double step = 2 * Math.PI / 100_000;
        IntStream.range(0, 100_000).forEach(i -> {
            double angle = i * step;
            calculatriceService.sinus(angle);
            calculatriceService.cosinus(angle);
            // Éviter les valeurs où tangente n'est pas définie
            if (Math.abs(angle - Math.PI/2) > 0.01 && Math.abs(angle - 3*Math.PI/2) > 0.01) {
                calculatriceService.tangente(angle);
            }
        });
    }

    @Test
    @DisplayName("Test de performance pour 10 000 calculs exponentiels et logarithmiques")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testPerformanceExpLog() {
        double step = 10.0 / 10_000;
        IntStream.range(0, 10_000).forEach(i -> {
            double value = 0.1 + i * step; // Éviter 0 pour log
            calculatriceService.exponentielle(value);
            calculatriceService.logarithmeNaturel(value);
        });
    }
}
