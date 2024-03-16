package org.example;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.HashMap;

public class OperationClass {
    public static String solveOperationForPolynomials(Polynomial poly1, Polynomial poly2, String operation) {
        Polynomial finalPolynomial = null;
        Polynomial[] polynomialsDivision = new Polynomial[3];
        poly1.readPolynomial();
        poly2.readPolynomial();
        switch (operation) {
            case "Addition" -> finalPolynomial = additionOfPolynomials(poly1, poly2);
            case "Subtraction" -> finalPolynomial = subtractionOfPolynomials(poly1, poly2);
            case "Multiplication" -> finalPolynomial = multiplicationOfPolynomials(poly1, poly2);
            case "Division" -> polynomialsDivision = divisionOfPolynomials(poly1, poly2);
            case "Differentiation" -> finalPolynomial = differentiationOfPolynomial(poly1);
            case "Integration" -> finalPolynomial = integrationOfPolynomial(poly1);
            default -> System.out.println("This did not work as expected");
        }
        if (operation.equals("Division")) {
            if (polynomialsDivision[0] == null && polynomialsDivision[1] == null)
                return "Cannot divide by 0.";
            else if(polynomialsDivision[1] == null)
                return polynomialsDivision[0].getPolynomialString();
            else if(polynomialsDivision[0] != null)
                return polynomialsDivision[0].getPolynomialString() + " +(" + polynomialsDivision[1].getPolynomialString() + ")/(" + polynomialsDivision[2].getPolynomialString() + ")";
        }
        else if(operation.equals("Integration"))
            return finalPolynomial.getPolynomialString() + " +C";

        assert finalPolynomial != null;
        return finalPolynomial.getPolynomialString();
    }

    private static String displayFinalPolynomial(HashMap<Integer, Double> finalMap) {
        StringBuilder finalText = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.##");
        int sign;
        for (HashMap.Entry<Integer, Double> entry : finalMap.entrySet()) {
            int degree = entry.getKey();
            double coefficient = entry.getValue();
            sign = Double.compare(coefficient, 0);
            if (coefficient != 0 && coefficient != 1 && coefficient != -1) {
                if (degree >= 2)
                    finalText.insert(0, df.format(coefficient) + "X^" + degree);
                else if (degree == 1)
                    finalText.insert(0, df.format(coefficient) + "X");
                else
                    finalText.insert(0, df.format(coefficient));
            } else if (coefficient == 1) {
                if (degree >= 2)
                    finalText.insert(0, "X^" + degree);
                else if (degree == 1)
                    finalText.insert(0, "X");
                else
                    finalText.insert(0, df.format(coefficient));
            } else if (coefficient == -1) {
                if (degree >= 2)
                    finalText.insert(0, "-X^" + degree);
                else if (degree == 1)
                    finalText.insert(0, "-X");
                else
                    finalText.insert(0, df.format(coefficient));
            }
            if (sign == 1)
                finalText.insert(0, " +");
            else if (sign == -1)
                finalText.insert(0, " ");
        }
        if (finalText.isEmpty())
            return "0";
        else if (finalText.length() > 1 && finalText.charAt(0) == ' ' && finalText.charAt(1) == '+')
            return finalText.substring(2, finalText.length());
        else if (finalText.charAt(0) == ' ')
            return finalText.substring(1, finalText.length());
        else
            return finalText.toString();
    }

    private static Polynomial createFinalPolynomialOfTypePolynomial(String polynomialText, HashMap<Integer, Double> coefficientMap) {
        Polynomial poly = new Polynomial(polynomialText);
        poly.setCoefficientMap(coefficientMap);
        return poly;
    }

    public static Polynomial additionOfPolynomials(Polynomial poly1, Polynomial poly2) {
        //additionForPolynomials
        HashMap<Integer, Double> firstMap = poly1.getCoefficientMap();
        HashMap<Integer, Double> secondMap = poly2.getCoefficientMap();
        HashMap<Integer, Double> resultingMap = new HashMap<>();

        for (HashMap.Entry<Integer, Double> entry : firstMap.entrySet()) {
            int degree = entry.getKey();
            double coefficient1 = entry.getValue();
            if (secondMap.containsKey(degree)) {
                double coefficient2 = secondMap.get(degree);
                double sumCoefficients = coefficient1 + coefficient2;
                resultingMap.put(degree, sumCoefficients);
                secondMap.remove(degree);
            } else
                resultingMap.put(degree, coefficient1);
        }
        for (HashMap.Entry<Integer, Double> entry : secondMap.entrySet()) //what remains left in the secondMap
        {
            int degree = entry.getKey();
            double coefficient = entry.getValue();
            resultingMap.put(degree, coefficient);
        }
        return createFinalPolynomialOfTypePolynomial(displayFinalPolynomial(resultingMap), resultingMap);
    }

    public static Polynomial subtractionOfPolynomials(Polynomial poly1, Polynomial poly2) {
        HashMap<Integer, Double> firstMap = poly1.getCoefficientMap();
        HashMap<Integer, Double> secondMap = poly2.getCoefficientMap();
        HashMap<Integer, Double> resultingMap = new HashMap<>();
        for (HashMap.Entry<Integer, Double> entry : firstMap.entrySet()) {
            int degree = entry.getKey();
            double coefficient1 = entry.getValue();
            if (secondMap.containsKey(degree)) {
                double coefficient2 = secondMap.get(degree);
                double sumCoefficients = coefficient1 + (-1) * coefficient2;
                resultingMap.put(degree, sumCoefficients);
                secondMap.remove(degree);
            } else
                resultingMap.put(degree, coefficient1);
        }
        for (HashMap.Entry<Integer, Double> entry : secondMap.entrySet()) {
            int degree = entry.getKey();
            double coefficient = -entry.getValue();
            resultingMap.put(degree, coefficient);
        }
        return createFinalPolynomialOfTypePolynomial(displayFinalPolynomial(resultingMap), resultingMap);
    }

    public static Polynomial multiplicationOfPolynomials(Polynomial poly1, Polynomial poly2) {
        HashMap<Integer, Double> firstMap = poly1.getCoefficientMap();
        HashMap<Integer, Double> secondMap = poly2.getCoefficientMap();
        HashMap<Integer, Double> resultingMap = new HashMap<>();
        for (HashMap.Entry<Integer, Double> entry1 : firstMap.entrySet()) {
            int degree1 = entry1.getKey();
            double coefficient1 = entry1.getValue();
            for (HashMap.Entry<Integer, Double> entry2 : secondMap.entrySet()) {
                int degree2 = entry2.getKey();
                double coefficient2 = entry2.getValue();
                int finalDegree = degree1 + degree2;
                double finalCoefficient = coefficient1 * coefficient2;
                resultingMap.put(finalDegree, resultingMap.getOrDefault(finalDegree, (double) 0) + finalCoefficient);
            }
        }
        return createFinalPolynomialOfTypePolynomial(displayFinalPolynomial(resultingMap), resultingMap);
    }

    private static int degreeOf(HashMap<Integer, Double> map) {
        int maxDegree = -1;
        for (int degree : map.keySet()) {
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }
        return maxDegree;
    }

    public static Polynomial[] divisionOfPolynomials(Polynomial poly1, Polynomial poly2) {
        HashMap<Integer, Double> remainderMap = poly1.getCoefficientMap();
        HashMap<Integer, Double> secondMap = poly2.getCoefficientMap();
        if (secondMap.isEmpty() || secondMap.getOrDefault(0, (double) 1) == 0)
            return new Polynomial[]{null, null};
        HashMap<Integer, Double> resultingMap = new HashMap<>();
        int highestPowerRemainder = degreeOf(remainderMap);
        int highestPowerSecond = degreeOf(secondMap);
        while (highestPowerRemainder >= highestPowerSecond) {
            int degree = highestPowerRemainder - highestPowerSecond;
            double coefficient;
            if (secondMap.getOrDefault(highestPowerSecond, (double) 0) != 0)
                coefficient = remainderMap.get(highestPowerRemainder) / secondMap.get(highestPowerSecond);
            else
                coefficient = 0;
            resultingMap.put(degree, coefficient); //does not matter if coefficient is 0 because upper function solves this case as well.

            for (HashMap.Entry<Integer, Double> entry : secondMap.entrySet()) {
                int newDegree = entry.getKey() + degree;
                double newCoefficient = entry.getValue() * coefficient;
                if (remainderMap.containsKey(newDegree))
                    remainderMap.put(newDegree, remainderMap.get(newDegree) - newCoefficient);
                else
                    remainderMap.put(newDegree, -newCoefficient);
            }
            remainderMap.remove(highestPowerRemainder);//this action is vital for correctness.
            highestPowerRemainder = degreeOf(remainderMap);
        }
        String remainderMapString = displayFinalPolynomial(remainderMap);
        if (remainderMap.isEmpty() || remainderMapString.equals("0"))
            return new Polynomial[]{createFinalPolynomialOfTypePolynomial(displayFinalPolynomial(resultingMap), resultingMap), null};

        Polynomial finalFirstPolynomial = createFinalPolynomialOfTypePolynomial(displayFinalPolynomial(resultingMap), resultingMap);
        Polynomial finalSecondPolynomial = createFinalPolynomialOfTypePolynomial(remainderMapString, remainderMap);
        Polynomial finalThirdPolynomial = createFinalPolynomialOfTypePolynomial(displayFinalPolynomial(secondMap), secondMap);
        return new Polynomial[]{finalFirstPolynomial, finalSecondPolynomial, finalThirdPolynomial};
    }

    public static Polynomial differentiationOfPolynomial(Polynomial poly1) {
        HashMap<Integer, Double> firstMap = poly1.getCoefficientMap();
        HashMap<Integer, Double> resultingMap = new HashMap<>();
        for (HashMap.Entry<Integer, Double> entry : firstMap.entrySet()) {
            if (entry.getKey() != 0) {
                if (entry.getValue() != 0) {
                    resultingMap.put(entry.getKey() - 1, entry.getValue() * entry.getKey());
                }
            }
        }
        return createFinalPolynomialOfTypePolynomial(displayFinalPolynomial(resultingMap), resultingMap);
    }

    public static Polynomial integrationOfPolynomial(Polynomial poly1) {
        HashMap<Integer, Double> firstMap = poly1.getCoefficientMap();
        HashMap<Integer, Double> resultingMap = new HashMap<>();
        for (HashMap.Entry<Integer, Double> entry : firstMap.entrySet()) {
            if (entry.getValue() != 0)
                resultingMap.put(entry.getKey() + 1, entry.getValue() / (entry.getKey() + 1));
        }
        return createFinalPolynomialOfTypePolynomial(displayFinalPolynomial(resultingMap), resultingMap);
    }
}