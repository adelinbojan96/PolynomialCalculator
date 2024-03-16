package org.example;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
public class PolynomialTest {

    @org.junit.jupiter.api.Test
    protected void readPolynomial()
    {
        Polynomial poly1 = new Polynomial("3x^2 + 2x + 5");
        Polynomial poly2 = new Polynomial("4x^2 + 3");
        Polynomial poly3 = new Polynomial("2x^3 + 4x^2 + x");
        Polynomial poly4 = new Polynomial("3x^2 - 2x + 1");
        Polynomial poly5 = new Polynomial("-2x^3 + 5x^2 - x + 3");
        Polynomial poly6 = new Polynomial("x^2 - 2x + 1");
        Polynomial poly7 = new Polynomial("0x^2 + 0");

        poly1.readPolynomial();
        poly2.readPolynomial();
        poly3.readPolynomial();
        poly4.readPolynomial();
        poly5.readPolynomial();
        poly6.readPolynomial();
        poly7.readPolynomial();

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

        HashMap<Integer, Double> expectedLastMap = new HashMap<>();
        expectedLastMap.put(2, 0.00);
        expectedLastMap.put(0, 0.00);

        assertEquals(expectedMap1_first, poly1.getCoefficientMap());
        assertEquals(expectedMap1_second, poly2.getCoefficientMap());
        assertEquals(expectedMap2_first, poly3.getCoefficientMap());
        assertEquals(expectedMap2_second, poly4.getCoefficientMap());
        assertEquals(expectedMap3_first, poly5.getCoefficientMap());
        assertEquals(expectedMap3_second, poly6.getCoefficientMap());
        assertEquals(expectedLastMap, poly7.getCoefficientMap());
    }
}
