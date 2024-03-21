package org.main;
import java.util.HashMap;
import java.util.regex.*;

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
    private void storeInHashMaps(String poly, HashMap<Integer, Double> coefficientMap)
    {
        poly = poly.replaceAll("\\s", ""); //remove all spaces
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(poly);
        while (matcher.find()) {
            String term = matcher.group(1);
            if (!term.isEmpty()) {
                double coefficient;
                int degree = 0;
                if (term.contains("X") || term.contains("x")) {
                    String[] parts = term.split("[xX]");
                    if (parts.length > 0 && !parts[0].isEmpty())
                    {
                        if(!parts[0].equals("+") && !parts[0].equals("-"))
                            coefficient = Double.parseDouble(parts[0]);
                        else if(parts[0].equals("+"))
                            coefficient = 1;
                        else
                            coefficient = -1;
                    }
                    else
                        coefficient = 1;//in case x is removed and there is nothing left
                    if (parts.length > 1 && parts[1].contains("^")) {
                        degree = Integer.parseInt(parts[1].substring(parts[1].indexOf("^") + 1));
                    } else
                        degree = 1;
                } else
                    coefficient = Double.parseDouble(term);
                addCoefficient(degree, coefficient, coefficientMap);}}
    }
    public void readPolynomial()
    {
        try {
            storeInHashMaps(this.poly1, coefficientMap1);
        } catch (Exception e) {
            System.err.println("Error reading polynomial: " + e.getMessage());
        }
    }

}
