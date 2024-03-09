package org.example;

public class Main {
    public static void main(String[] args) {
        new GUI_Class();
        String polynomial = "-x^7 + x^6 - 3x^2 + x - 4";
        Polynomial poly1 = new Polynomial(polynomial, polynomial, "nothing");
        poly1.readPolynomials();
    }
}