package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Polynomial {
    private String poly1;
    private String poly2;
    private final HashMap<Integer, Integer> coefficientMap1;
    private final HashMap<Integer, Integer> coefficientMap2;
    public Polynomial(String poly1, String poly2) {
        this.poly1 = poly1;
        this.poly2 = poly2;
        coefficientMap1 = new HashMap<>();
        coefficientMap2 = new HashMap<>();
    }
    public void addCoefficient(int degree, int coefficient, HashMap<Integer, Integer> coefficientMap) {
        coefficientMap.put(degree, coefficient);
    }
    private void storeInHashMaps(String poly, HashMap<Integer, Integer> coefficientMap) {
        int degree = 0;
        int prevDegree = 0;
        int coefficient = 0;
        boolean isDegree = false;
        int sign = 1; // -1 if negative or 1 if positive
        ArrayList<Integer> degrees = new ArrayList<>();
        ArrayList<Integer> coefficients = new ArrayList<>();
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
            } if (character == '+' || character == '-') {
                if (coefficient == 0) {
                    coefficient = 1;
                }
                if(isDegree && degree == 0)
                    degree = 1;
                addCoefficient(degree,coefficient * sign, coefficientMap);
                prevDegree = degree;
                isDegree = false;
                degrees.add(degree);
                coefficients.add(coefficient * sign);
                coefficient = 0;
                sign = (character == '+') ? 1 : -1;
            }
        }
        if (coefficient != 0) {
            if(isDegree && degree == 0)
                degree = 1;
            if(prevDegree == degree)
                degree = 0;
            addCoefficient(degree, coefficient, coefficientMap);
            degrees.add(degree);
            coefficients.add(coefficient * sign);
        }
    /*  TESTING STUFF
        for (Integer value : degrees) {
            System.out.println("Degree: " + value);
        }
        for (Integer integer : coefficients) {
            System.out.println("Coefficient: " + integer);
        }
     */
    }
    public void readPolynomials()
    {
        storeInHashMaps(this.poly1, coefficientMap1);
        storeInHashMaps(this.poly2, coefficientMap2);
    }
}
