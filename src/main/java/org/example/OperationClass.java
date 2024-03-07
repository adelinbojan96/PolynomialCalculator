package org.example;

import javax.swing.*;
import java.util.Objects;

public class OperationClass {
    //here we are going to implement the operations
    public void displaySecondPolynomial(JComboBox operationChooser, JTextArea secondPolynomialText, JLabel secondText)
    {
        operationChooser.addActionListener(e -> {
            String selectedOperation = (String) operationChooser.getSelectedItem();
            if (selectedOperation.equals("Differentiation") || selectedOperation.equals("Integration")) {
                secondText.setVisible(false);
                secondPolynomialText.setVisible(false);
            } else {
                secondText.setVisible(true);
                secondPolynomialText.setVisible(true);
            }
        });
    }
}
