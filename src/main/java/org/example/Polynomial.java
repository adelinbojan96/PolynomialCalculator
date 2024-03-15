package org.example;
import java.util.HashMap;
public class Polynomial {
    public String getPolynomialString() {
        return poly1;
    }

    private final String poly1;
    private HashMap<Integer, Double> coefficientMap1 = new HashMap<>();

    public HashMap<Integer, Double> getCoefficientMap() {
        return coefficientMap1;
    }
    public void setCoefficientMap(HashMap<Integer, Double> coefficientMap1)
    {
        this.coefficientMap1 = coefficientMap1;
    }

    public Polynomial(String poly1) {
        this.poly1 = poly1;
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
    protected void readPolynomial()
    {
        storeInHashMaps(this.poly1, coefficientMap1);
    }

}
