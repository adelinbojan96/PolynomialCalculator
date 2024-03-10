package org.example;
import java.util.HashMap;

public class Polynomial {
    private final String poly1;
    private final String poly2;

    private final String operation;
    private final HashMap<Integer, Double> coefficientMap1;
    private final HashMap<Integer, Double> coefficientMap2;

    public HashMap<Integer, Double> getCoefficientMap1() {
        return coefficientMap1;
    }

    public HashMap<Integer, Double> getCoefficientMap2() {
        return coefficientMap2;
    }

    public Polynomial(String poly1, String poly2, String operation) {
        this.poly1 = poly1;
        this.poly2 = poly2;
        coefficientMap1 = new HashMap<>();
        coefficientMap2 = new HashMap<>();
        this.operation = operation;
    }
    private void addCoefficient(int degree, double coefficient, HashMap<Integer, Double> coefficientMap) {
        coefficientMap.put(degree, coefficient);
    }
    private void storeInHashMaps(String poly, HashMap<Integer, Double> coefficientMap) {
        int degree = 0;
        int prevDegree = 0;
        int coefficient = 0;
        boolean isDegree = false;
        int sign = 1; // -1 if negative or 1 if positive
        boolean skip = false;
        if (!poly.isEmpty() && poly.charAt(0) == '-') {
            sign = -1;
            skip = true;
        }
        for (char character : poly.toCharArray()) {
            if(skip)
            {
                skip = false;//skips the first iteration if we have a negative polynomial
                continue;
            }
            if (Character.isDigit(character) && !isDegree) { // if it is coefficient
                int digit = Character.getNumericValue(character);
                coefficient = coefficient * 10 + digit;
            } else if (isDegree && Character.isDigit(character)) {
                    int digit = Character.getNumericValue(character);
                    degree = degree * 10 + digit;
            } if (character == 'x' || character == 'X') {
                degree = 0;
                isDegree = true;
                if (coefficient == 0) {
                    coefficient = 1;
                }
            } if (character == '+' || character == '-') {
                if(isDegree && degree == 0)
                    degree = 1;
                addCoefficient(degree,coefficient * sign, coefficientMap);
                prevDegree = degree;
                isDegree = false;
                coefficient = 0;
                sign = (character == '+') ? 1 : -1;
            }
        }
        if (coefficient != 0) {
            if(isDegree && degree == 0)
                degree = 1;
            if(prevDegree == degree)
                degree = 0;
            addCoefficient(degree, coefficient * sign, coefficientMap);
        }
    }
    protected void readPolynomials()
    {
        storeInHashMaps(this.poly1, coefficientMap1);
        storeInHashMaps(this.poly2, coefficientMap2);
    }

    public String solveOperationForPolynomial() {
        String finalPolynomial = "";
        readPolynomials();
        switch (operation) {
            case "Addition" -> finalPolynomial = OperationClass.additionOfPolynomials(this);
            case "Subtraction" -> finalPolynomial = OperationClass.subtractionOfPolynomials(this);
            case "Multiplication" -> finalPolynomial = OperationClass.multiplicationOfPolynomials(this);
            case "Division" -> finalPolynomial = OperationClass.divisionOfPolynomials(this);
            case "Differentiation" -> finalPolynomial = OperationClass.differentiationOfPolynomial(this);
            case "Integration" -> finalPolynomial = OperationClass.integrationOfPolynomial(this);
            default -> System.out.println("This did not work as expected");
        }
        return finalPolynomial;
    }
}
