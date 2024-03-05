package org.example;

import javax.swing.*;

public class GUI_Class extends JDialog {
    private JPanel mainPanel;
    private JTextArea firstPolynomialText;
    private JComboBox operationChooser;
    private JButton makeTheOperationButton;
    private JTextArea secondPolynomialText;
    private JTextArea finalText;

    public GUI_Class(JFrame parent)
    {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(850, 600);
        setTitle("Enter the polynomials in order to perform certain operations");
        setContentPane(mainPanel);
        setLocationRelativeTo(parent);
        DesignClass design = new DesignClass();
        design.customizeButton(makeTheOperationButton);
        design.customizeChooser(operationChooser);
        setModal(true);
        setVisible(true);
    }
}
