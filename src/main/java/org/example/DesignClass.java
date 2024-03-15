package org.example;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
public class DesignClass {
    public void customizeButton(JButton button)
    {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 1, true),
                new EmptyBorder(5, 20, 5, 20)
        ));
    }
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
    public void customizeChooser(JComboBox operationChooser)
    {
        operationChooser.addItem("Addition");
        operationChooser.addItem("Subtraction");
        operationChooser.addItem("Multiplication");
        operationChooser.addItem("Division");
        operationChooser.addItem("Differentiation");
        operationChooser.addItem("Integration");
        operationChooser.setBackground(Color.WHITE);
        operationChooser.setForeground(Color.BLACK);
        operationChooser.setFont(new Font("Consolas", Font.PLAIN, 14));
        operationChooser.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));}}