package org.example;

import static org.junit.jupiter.api.Assertions.*;

class OperationClassTest {
    private void callRead(Polynomial []polynomials)
    {
        for (Polynomial poly: polynomials) {
            poly.readPolynomial();
        }
    }

    @org.junit.jupiter.api.Test
    void additionOfPolynomials() {
        Polynomial poly1 = new Polynomial("3x^2 + 2x + 5");
        Polynomial poly2 = new Polynomial("4x^2 + 3");
        Polynomial poly3 = new Polynomial("2x^3 + 4x^2 + x");
        Polynomial poly4 = new Polynomial("3x^2 - 2x + 1");
        Polynomial poly5 = new Polynomial("-X^2 - 3x + 76");
        Polynomial poly6 = new Polynomial("-41 + 53x");
        callRead(new Polynomial[]{poly1, poly2, poly3, poly4, poly5, poly6});

        assertEquals("7X^2 +2X +8", OperationClass.solveOperationForPolynomials(poly1, poly2, "Addition"));
        assertEquals("2X^3 +7X^2 -X +1", OperationClass.solveOperationForPolynomials(poly3, poly4, "Addition"));
        assertEquals("-X^2 +50X +35", OperationClass.solveOperationForPolynomials(poly5, poly6, "Addition"));
    }

    @org.junit.jupiter.api.Test
    void subtractionOfPolynomials() {
        Polynomial poly1 = new Polynomial("3x^2 + 2x + 5");
        Polynomial poly2 = new Polynomial("4x^2 + 3");
        Polynomial poly3 = new Polynomial("2x^3 + 4x^2 + x");
        Polynomial poly4 = new Polynomial("-2x^3 + 4x^2 + 9");
        Polynomial poly5 = new Polynomial("-X^2 - 3x + 76");
        Polynomial poly6 = new Polynomial("-41 + 53x");
        callRead(new Polynomial[]{poly1, poly2, poly3, poly4, poly5, poly6});

        assertEquals("-X^2 +2X +2", OperationClass.solveOperationForPolynomials(poly1, poly2, "Subtraction"));
        assertEquals("4X^3 +X -9", OperationClass.solveOperationForPolynomials(poly3, poly4, "Subtraction"));
        assertEquals("-X^2 -56X +117", OperationClass.solveOperationForPolynomials(poly5, poly6, "Subtraction"));
    }

    @org.junit.jupiter.api.Test
    void multiplicationOfPolynomials() {
        Polynomial poly1 = new Polynomial("X - 1");
        Polynomial poly2 = new Polynomial("X + 1");
        Polynomial poly3 = new Polynomial("3x^2 + 2x + 5");
        Polynomial poly4 = new Polynomial("-41 + 53x");
        Polynomial poly5 = new Polynomial("x^3 -3X^2 - 5X +9");
        Polynomial poly6 = new Polynomial("X -2");
        Polynomial poly7 = new Polynomial("4");
        Polynomial poly8 = new Polynomial("0");
        callRead(new Polynomial[]{poly1, poly2, poly3, poly4, poly5, poly6, poly7, poly8});

        assertEquals("X^2 -1", OperationClass.solveOperationForPolynomials(poly1, poly2, "Multiplication"));
        assertEquals("159X^3 -17X^2 +183X -205", OperationClass.solveOperationForPolynomials(poly3, poly4, "Multiplication"));
        assertEquals("X^4 -5X^3 +X^2 +19X -18", OperationClass.solveOperationForPolynomials(poly5, poly6, "Multiplication"));
        assertEquals("0", OperationClass.solveOperationForPolynomials(poly7, poly8, "Multiplication"));
    }

    @org.junit.jupiter.api.Test
    void divisionOfPolynomials() {
        Polynomial poly1 = new Polynomial("X - 1");
        Polynomial poly2 = new Polynomial("X + 1");
        Polynomial poly3 = new Polynomial("0");
        Polynomial poly4 = new Polynomial("X -3");
        Polynomial poly5 = new Polynomial( "X^4 -3X + 2");
        Polynomial poly6 = new Polynomial("0");
        Polynomial poly7 = new Polynomial("4x^3 + 7x^2 - 5x + 9");
        Polynomial poly8 = new Polynomial("X -3");
        callRead(new Polynomial[]{poly1, poly2, poly3, poly4, poly5, poly6, poly7, poly8});

        assertEquals("1 +(-2)/(X +1)", OperationClass.solveOperationForPolynomials(poly1, poly2, "Division"));
        assertEquals("0", OperationClass.solveOperationForPolynomials(poly3, poly4, "Division"));
        assertEquals("Cannot divide by 0.", OperationClass.solveOperationForPolynomials(poly5, poly6, "Division"));
        assertEquals("4X^2 +19X +52 +(165)/(X -3)", OperationClass.solveOperationForPolynomials(poly7, poly8, "Division"));
    }

    @org.junit.jupiter.api.Test
    void differentiationOfPolynomial() {
        Polynomial poly1 = new Polynomial("X - 1");
        Polynomial poly2 = new Polynomial("0");
        Polynomial poly3 = new Polynomial( "X^4 -3X + 2");
        Polynomial poly4 = new Polynomial("4x^3 + 7x^2 - 5x + 9");
        callRead(new Polynomial[]{poly1, poly2, poly3, poly4});

        assertEquals("1", OperationClass.solveOperationForPolynomials(poly1, poly1, "Differentiation"));
        assertEquals("0", OperationClass.solveOperationForPolynomials(poly2, poly2, "Differentiation"));
        assertEquals("4X^3 -3", OperationClass.solveOperationForPolynomials(poly3, poly3, "Differentiation"));
        assertEquals("12X^2 +14X -5", OperationClass.solveOperationForPolynomials(poly4, poly4, "Differentiation"));
    }

    @org.junit.jupiter.api.Test
    void integrationOfPolynomial() {
        Polynomial poly1 = new Polynomial("2X - 1");
        Polynomial poly2 = new Polynomial("0");
        Polynomial poly3 = new Polynomial( "X^4 -3X + 2");
        Polynomial poly4 = new Polynomial("4x^3 + 7x^2 - 5x + 9");
        callRead(new Polynomial[]{poly1, poly2, poly3, poly4});

        assertEquals("X^2 -X +C", OperationClass.solveOperationForPolynomials(poly1, poly1, "Integration"));
        assertEquals("0 +C", OperationClass.solveOperationForPolynomials(poly2, poly2, "Integration"));
        assertEquals("0.2X^5 -1.5X^2 +2X +C", OperationClass.solveOperationForPolynomials(poly3, poly3, "Integration"));
        assertEquals("X^4 +2.33X^3 -2.5X^2 +9X +C", OperationClass.solveOperationForPolynomials(poly4, poly4, "Integration"));// 8/3 = 2.33
    }
}