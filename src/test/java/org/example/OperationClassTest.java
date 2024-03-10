package org.example;

import static org.junit.jupiter.api.Assertions.*;

class OperationClassTest {
    private void callRead(Polynomial []polynomials)
    {
        for (Polynomial poly: polynomials) {
            poly.readPolynomials();
        }
    }

    @org.junit.jupiter.api.Test
    void additionOfPolynomials() {
        Polynomial poly1 = new Polynomial("3x^2 + 2x + 5", "4x^2 + 3", "Addition");
        Polynomial poly2 = new Polynomial("2x^3 + 4x^2 + x", "3x^2 - 2x + 1", "Addition");
        Polynomial poly3 = new Polynomial("-X^2 - 3x + 76", "-41 + 53x", "Addition");
        callRead(new Polynomial[]{poly1, poly2, poly3});

        assertEquals("7X^2 +2X +8", poly1.solveOperationForPolynomial());
        assertEquals("2X^3 +7X^2 -X +1", poly2.solveOperationForPolynomial());
        assertEquals("-X^2 +50X +35", poly3.solveOperationForPolynomial());
    }

    @org.junit.jupiter.api.Test
    void subtractionOfPolynomials() {
        Polynomial poly1 = new Polynomial("3x^2 + 2x + 5", "4x^2 + 3", "Subtraction");
        Polynomial poly2 = new Polynomial("2x^3 + 4x^2 + x", "-2x^3 + 4x^2 + 9", "Subtraction");
        Polynomial poly3 = new Polynomial("-X^2 - 3x + 76", "-41 + 53x", "Subtraction");
        callRead(new Polynomial[]{poly1, poly2, poly3});

        assertEquals("-X^2 +2X +2", poly1.solveOperationForPolynomial());
        assertEquals("4X^3 +X -9", poly2.solveOperationForPolynomial());
        assertEquals("-X^2 -56X +117", poly3.solveOperationForPolynomial());
    }

    @org.junit.jupiter.api.Test
    void multiplicationOfPolynomials() {
        Polynomial poly1 = new Polynomial("X - 1", "X + 1", "Multiplication");
        Polynomial poly2 = new Polynomial("3x^2 + 2x + 5", "-41 + 53x", "Multiplication");
        Polynomial poly3 = new Polynomial("x^3 -3X^2 - 5X +9", "X -2", "Multiplication");
        Polynomial poly4 = new Polynomial("4", "0", "Multiplication");
        callRead(new Polynomial[]{poly1, poly2, poly3});

        assertEquals("X^2 -1", poly1.solveOperationForPolynomial());
        assertEquals("159X^3 -17X^2 +183X -205", poly2.solveOperationForPolynomial());
        assertEquals("X^4 -5X^3 +X^2 +19X -18", poly3.solveOperationForPolynomial());
        assertEquals("0", poly4.solveOperationForPolynomial());
    }

    @org.junit.jupiter.api.Test
    void divisionOfPolynomials() {
        Polynomial poly1 = new Polynomial("X - 1", "X + 1", "Division");
        Polynomial poly2 = new Polynomial("0", "x -3", "Division");
        Polynomial poly3 = new Polynomial( "X^4 -3X + 2", "0", "Division");
        Polynomial poly4 = new Polynomial("4x^3 + 7x^2 - 5x + 9", "x-3", "Division");
        callRead(new Polynomial[]{poly1, poly2, poly3});

        assertEquals("1 +(-2)/(X +1)", poly1.solveOperationForPolynomial());
        assertEquals("0", poly2.solveOperationForPolynomial());
        assertEquals("Cannot divide by 0", poly3.solveOperationForPolynomial());
        assertEquals("4X^2 +19X +52 +(165)/(X -3)", poly4.solveOperationForPolynomial());
    }

    @org.junit.jupiter.api.Test
    void differentiationOfPolynomial() {
        Polynomial poly1 = new Polynomial("X - 1", "", "Differentiation");
        Polynomial poly2 = new Polynomial("0", "", "Differentiation");
        Polynomial poly3 = new Polynomial( "X^4 -3X + 2", "", "Differentiation");
        Polynomial poly4 = new Polynomial("4x^3 + 7x^2 - 5x + 9", "", "Differentiation");
        callRead(new Polynomial[]{poly1, poly2, poly3});

        assertEquals("1", poly1.solveOperationForPolynomial());
        assertEquals("0", poly2.solveOperationForPolynomial());
        assertEquals("4X^3 -3", poly3.solveOperationForPolynomial());
        assertEquals("12X^2 +14X -5", poly4.solveOperationForPolynomial());
    }

    @org.junit.jupiter.api.Test
    void integrationOfPolynomial() {
        Polynomial poly1 = new Polynomial("2X - 1", "", "Integration");
        Polynomial poly2 = new Polynomial("0", "", "Integration");
        Polynomial poly3 = new Polynomial( "X^4 -3X + 2", "", "Integration");
        Polynomial poly4 = new Polynomial("4x^3 + 7x^2 - 5x + 9", "", "Integration");
        callRead(new Polynomial[]{poly1, poly2, poly3});

        assertEquals("X^2 -X +C", poly1.solveOperationForPolynomial());
        assertEquals("0 +C", poly2.solveOperationForPolynomial());
        assertEquals("0.2X^5 -1.5X^2 +2X +C", poly3.solveOperationForPolynomial());
        assertEquals("X^4 +2.33X^3 -2.5X^2 +9X +C", poly4.solveOperationForPolynomial());// 8/3 = 2.33
    }
}