package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GUI_Class extends JDialog {
    private JPanel mainPanel;
    private JTextArea firstPolynomialText;
    private JComboBox operationChooser;
    private JButton makeTheOperationButton;
    private JTextArea secondPolynomialText;
    private JTextArea finalText;
    private JLabel secondText;
    public GUI_Class()
    {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(850, 600);
        setTitle("Enter the polynomials in order to perform certain operations");
        setContentPane(mainPanel);
        DesignClass design = new DesignClass(); OperationClass operationClass = new OperationClass();
        design.customizeButton(makeTheOperationButton);
        design.customizeChooser(operationChooser);
        operationClass.displaySecondPolynomial(operationChooser, secondPolynomialText, secondText);
        makeTheOperationButton.addActionListener(e -> {
            //perform solving
            Polynomial poly = new Polynomial(firstPolynomialText.getText(), secondPolynomialText.getText(), Objects.requireNonNull(operationChooser.getSelectedItem()).toString());
            finalText.setText(poly.solveOperationForPolynomial());
        });
        setModal(true);
        setVisible(true);
    }
}
