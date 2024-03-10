package org.example;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
public class PolynomialTest {

    @org.junit.jupiter.api.Test
    protected void readPolynomials()
    {
        Polynomial poly1 = new Polynomial("3x^2 + 2x + 5", "4x^2 + 3", "Addition");
        Polynomial poly2 = new Polynomial("2x^3 + 4x^2 + x", "3x^2 - 2x + 1", "Subtraction");
        Polynomial poly3 = new Polynomial("-2x^3 + 5x^2 - x + 3", "x^2 - 2x + 1", "Multiplication");

        poly1.readPolynomials();
        poly2.readPolynomials();
        poly3.readPolynomials();

        HashMap<Integer, Double> expectedMap1_first = new HashMap<>();
        expectedMap1_first.put(2, 3.00);
        expectedMap1_first.put(1, 2.00);
        expectedMap1_first.put(0, 5.00);

        HashMap<Integer, Double> expectedMap1_second = new HashMap<>();
        expectedMap1_second.put(2, 4.00);
        expectedMap1_second.put(0, 3.00);

        HashMap<Integer, Double> expectedMap2_first = new HashMap<>();
        expectedMap2_first.put(3, 2.00);
        expectedMap2_first.put(2, 4.00);
        expectedMap2_first.put(1, 1.00);

        HashMap<Integer, Double> expectedMap2_second = new HashMap<>();
        expectedMap2_second.put(2, 3.00);
        expectedMap2_second.put(1, -2.00);
        expectedMap2_second.put(0, 1.00);

        HashMap<Integer, Double> expectedMap3_first = new HashMap<>();
        expectedMap3_first.put(3, -2.00);
        expectedMap3_first.put(2, 5.00);
        expectedMap3_first.put(1, -1.00);
        expectedMap3_first.put(0, 3.00);

        HashMap<Integer, Double> expectedMap3_second = new HashMap<>();
        expectedMap3_second.put(2, 1.00);
        expectedMap3_second.put(1, -2.00);
        expectedMap3_second.put(0, 1.00);

        assertEquals(expectedMap1_first, poly1.getCoefficientMap1());
        assertEquals(expectedMap1_second, poly1.getCoefficientMap2());
        assertEquals(expectedMap2_first, poly2.getCoefficientMap1());
        assertEquals(expectedMap2_second, poly2.getCoefficientMap2());
        assertEquals(expectedMap3_first, poly3.getCoefficientMap1());
        assertEquals(expectedMap3_second, poly3.getCoefficientMap2());
    }
}
