package org.example;

import javax.swing.*;
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
            return displayFinalPolynomial(resultingMap);
        }
        public static void multiplicationOfPolynomials (Polynomial poly)
        {
            HashMap<Integer, Integer> firstMap = poly.getCoefficientMap1();
            HashMap<Integer, Integer> secondMap = poly.getCoefficientMap2();
        }
        public static void divisionOfPolynomials (Polynomial poly)
        {
            HashMap<Integer, Integer> firstMap = poly.getCoefficientMap1();
            HashMap<Integer, Integer> secondMap = poly.getCoefficientMap2();
        }
        public static void differentiationOfPolynomial (Polynomial poly)
        {
            HashMap<Integer, Integer> firstMap = poly.getCoefficientMap1();
            HashMap<Integer, Integer> secondMap = poly.getCoefficientMap2();
        }
        public static void integrationOfPolynomial (Polynomial poly)
        {
            HashMap<Integer, Integer> firstMap = poly.getCoefficientMap1();
            HashMap<Integer, Integer> secondMap = poly.getCoefficientMap2();
        }
    }