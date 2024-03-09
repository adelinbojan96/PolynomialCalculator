package org.example;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OperationClass {
    //here we are going to implement the operations
    public void displaySecondPolynomial(JComboBox operationChooser, JTextArea secondPolynomialText, JLabel secondText)
    {
        operationChooser.addActionListener(e -> {
            String selectedOperation = (String) operationChooser.getSelectedItem();
            assert selectedOperation != null;
            if (selectedOperation.equals("Differentiation") || selectedOperation.equals("Integration")) {
                secondText.setVisible(false);
                secondPolynomialText.setVisible(false);
            } else {
                secondText.setVisible(true);
                secondPolynomialText.setVisible(true);
            }
        });
    }
    private static HashMap<Integer, Integer> firstMap = new HashMap<>();
    private static HashMap<Integer, Integer> secondMap = new HashMap<>();
    private static String displayFinalPolynomial(HashMap<Integer, Integer> finalMap)
    {
        StringBuilder finalText = new StringBuilder();
        int sign;
        for (HashMap.Entry<Integer, Integer> entry : finalMap.entrySet())
        {
            int degree = entry.getKey();
            int coefficient = entry.getValue();
            sign = Integer.compare(coefficient, 0);
            if(coefficient != 0 && coefficient != 1 && coefficient != -1)
            {
                if(degree >= 2)
                    finalText.insert(0,coefficient + "X^" + degree);
                else if(degree == 1)
                    finalText.insert(0, coefficient + "X");
                else
                    finalText.insert(0, coefficient);
            }
            else if(coefficient == 1)
            {
                if(degree >= 2)
                    finalText.insert(0,"X^" + degree);
                else if(degree == 1)
                    finalText.insert(0, "X");
                else
                    finalText.insert(0, coefficient);
            }
            else if(coefficient == -1)
            {
                if(degree >= 2)
                    finalText.insert(0,"-X^" + degree);
                else if(degree == 1)
                    finalText.insert(0, "-X");
                else
                    finalText.insert(0, coefficient);
            }
            if(sign == 1)
                finalText.insert(0, " +");
            else if(sign == -1)
                finalText.insert(0, " ");
        }
        if(finalText.isEmpty())
            return "0";
        else if(finalText.charAt(0) == ' ' && finalText.charAt(1) == '+')
            return finalText.substring(2, finalText.length());
        else if(finalText.charAt(0) == ' ')
            return finalText.substring(1, finalText.length());
        else
            return finalText.toString();
    }
    private static String displayFinalPolynomial2(HashMap<Integer, Double> finalMap)
    {
        StringBuilder finalText = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.##");
        int sign;
        for (HashMap.Entry<Integer, Double> entry : finalMap.entrySet())
        {
            int degree = entry.getKey();
            double coefficient = entry.getValue();
            sign = Double.compare(coefficient, 0);
            if(coefficient != 0 && coefficient != 1 && coefficient != -1)
            {
                if(degree >= 2)
                    finalText.insert(0,df.format(coefficient) + "X^" + degree);
                else if(degree == 1)
                    finalText.insert(0, df.format(coefficient) + "X");
                else
                    finalText.insert(0, df.format(coefficient));
            }
            else if(coefficient == 1)
            {
                if(degree >= 2)
                    finalText.insert(0,"X^" + degree);
                else if(degree == 1)
                    finalText.insert(0, "X");
                else
                    finalText.insert(0, df.format(coefficient));
            }
            else if(coefficient == -1)
            {
                if(degree >= 2)
                    finalText.insert(0,"-X^" + degree);
                else if(degree == 1)
                    finalText.insert(0, "-X");
                else
                    finalText.insert(0, df.format(coefficient));
            }
            if(sign == 1)
                finalText.insert(0, " +");
            else if(sign == -1)
                finalText.insert(0, " ");
        }
        if(finalText.isEmpty())
            return "0";
        else if(finalText.charAt(0) == ' ' && finalText.charAt(1) == '+')
            return finalText.substring(2, finalText.length());
        else if(finalText.charAt(0) == ' ')
            return finalText.substring(1, finalText.length());
        else
            return finalText.toString();
    }
    public static String additionOfPolynomials(Polynomial poly) {
        //additionForPolynomials
        firstMap = poly.getCoefficientMap1();
        secondMap = poly.getCoefficientMap2();
        HashMap <Integer, Integer> resultingMap = new HashMap<>();
        System.out.println("First one");
        for(HashMap.Entry entry : firstMap.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("Second one");
        for(HashMap.Entry entry : secondMap.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        for (HashMap.Entry<Integer, Integer> entry : firstMap.entrySet())
        {
            int degree = entry.getKey();
            int coefficient1 = entry.getValue();
            if (secondMap.containsKey(degree))
            {
                int coefficient2 = secondMap.get(degree);
                int sumCoefficients = coefficient1 + coefficient2;
                resultingMap.put(degree, sumCoefficients);
                secondMap.remove(degree);
            }
            else
                resultingMap.put(degree, coefficient1);
        }
        for(HashMap.Entry<Integer, Integer> entry : secondMap.entrySet()) //what remains left in the secondMap
        {
            int degree = entry.getKey();
            int coefficient = entry.getValue();
            resultingMap.put(degree, coefficient);
        }
        return displayFinalPolynomial(resultingMap);
    }
        public static String subtractionOfPolynomials (Polynomial poly)
        {
            HashMap<Integer, Integer> firstMap = poly.getCoefficientMap1();
            HashMap<Integer, Integer> secondMap = poly.getCoefficientMap2();
            HashMap <Integer, Integer> resultingMap = new HashMap<>();
            for (HashMap.Entry<Integer, Integer> entry : firstMap.entrySet()) {
                int degree = entry.getKey();
                int coefficient1 = entry.getValue();
                if (secondMap.containsKey(degree)) {
                    int coefficient2 = secondMap.get(degree);
                    int sumCoefficients = coefficient1 + (-1) * coefficient2;
                    resultingMap.put(degree, sumCoefficients);
                    secondMap.remove(degree);
                } else
                    resultingMap.put(degree, coefficient1);
            }
            for(HashMap.Entry<Integer, Integer> entry : secondMap.entrySet())
            {
                int degree = entry.getKey();
                int coefficient = -entry.getValue();
                resultingMap.put(degree, coefficient);
            }
            return displayFinalPolynomial(resultingMap);
        }
        public static String multiplicationOfPolynomials (Polynomial poly)
        {
            HashMap<Integer, Integer> firstMap = poly.getCoefficientMap1();
            HashMap<Integer, Integer> secondMap = poly.getCoefficientMap2();
            HashMap <Integer, Integer> resultingMap = new HashMap<>();
            for(HashMap.Entry<Integer, Integer> entry1 : firstMap.entrySet())
            {
                int degree1 = entry1.getKey();
                int coefficient1 = entry1.getValue();
                for(HashMap.Entry<Integer, Integer> entry2 : secondMap.entrySet())
                {
                    int degree2 = entry2.getKey();
                    int coefficient2 = entry2.getValue();
                    int finalDegree = degree1 + degree2;
                    int finalCoefficient = coefficient1 * coefficient2;
                    resultingMap.put(finalDegree, resultingMap.getOrDefault(finalDegree, 0) + finalCoefficient);
                }
            }
            return displayFinalPolynomial(resultingMap);
        }
        private static int degreeOf(HashMap<Integer, Integer> map) {
            int maxDegree = -1;
            for (int degree : map.keySet()) {
                if (degree > maxDegree) {
                     maxDegree = degree;
                 }
            }
            return maxDegree;
        }
        public static String divisionOfPolynomials (Polynomial poly)
        {
            HashMap<Integer, Integer> firstMap = poly.getCoefficientMap1();
            HashMap<Integer, Integer> secondMap = poly.getCoefficientMap2();
            HashMap <Integer, Integer> resultingMap = new HashMap<>();
            HashMap <Integer, Integer> remainderMap = firstMap;
            int highestPowerRemainder = degreeOf(remainderMap);
            int highestPowerSecond = degreeOf(secondMap);
            while(highestPowerRemainder >= highestPowerSecond)
            {
                int degree = highestPowerRemainder - highestPowerSecond;
                int coefficient;
                System.out.println(highestPowerRemainder + " " + highestPowerSecond + " and " + firstMap.get(highestPowerRemainder) + " " + secondMap.get(highestPowerSecond));
                if(secondMap.getOrDefault(highestPowerSecond, 0) != 0)
                    coefficient = firstMap.get(highestPowerRemainder) / secondMap.get(highestPowerSecond);
                else
                    coefficient = 0;
                resultingMap.put(degree, coefficient); //does not matter if coefficient is 0 because upper function solves everything.

                for(HashMap.Entry<Integer, Integer> entry: secondMap.entrySet())
                {
                    int newDegree = entry.getKey() + degree;
                    int newCoefficient = entry.getValue() * coefficient;
                    if(remainderMap.containsKey(newDegree))
                        remainderMap.put(newDegree, remainderMap.get(newDegree) - newCoefficient);
                    else
                        remainderMap.put(newDegree, - newCoefficient);
                }
                remainderMap.remove(highestPowerRemainder);//this action NEEDS to be done
                highestPowerRemainder = degreeOf(remainderMap);
            }
            if(remainderMap.isEmpty())
                return displayFinalPolynomial(resultingMap);
            return displayFinalPolynomial(resultingMap) + " +(" + displayFinalPolynomial(remainderMap) + ")/(" + displayFinalPolynomial(secondMap) + ")";
        }
        public static String differentiationOfPolynomial (Polynomial poly)
        {
            HashMap<Integer, Integer> firstMap = poly.getCoefficientMap1();
            HashMap<Integer, Integer> resultingMap = new HashMap<>();
            for(HashMap.Entry <Integer, Integer> entry : firstMap.entrySet())
            {
                System.out.println(entry.getKey() +" "+ entry.getValue());
                if(entry.getKey() != 0)
                {
                    if(entry.getValue() != 0) {
                        resultingMap.put(entry.getKey() - 1, entry.getValue() * entry.getKey());
                    }
                    else if(entry.getKey() > 0) {
                        resultingMap.put(entry.getKey() - 1, entry.getKey());
                    }
                }
            }
            return displayFinalPolynomial(resultingMap);
        }
        public static String integrationOfPolynomial (Polynomial poly)
        {
            HashMap<Integer, Integer> firstMap = poly.getCoefficientMap1();
            HashMap<Integer, Double> resultingMap = new HashMap<>();
            boolean constant = false;
            for(HashMap.Entry <Integer, Integer> entry : firstMap.entrySet())
            {
                if(entry.getValue() != 0)
                    resultingMap.put(entry.getKey() + 1, entry.getValue().doubleValue() / (entry.getKey() + 1));
            }
            return displayFinalPolynomial2(resultingMap) + " +C";
        }
    }