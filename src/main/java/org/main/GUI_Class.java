package org.main;
import org.design.DesignClass;

import javax.swing.*;
import java.awt.*;
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
        setSize(850, 460);
        setTitle("Enter the polynomials in order to perform certain operations");
        setContentPane(mainPanel);
        mainPanel.setBackground(Color.decode("#fdfa72"));
        DesignClass.customizeButton(makeTheOperationButton);
        DesignClass.customizeChooser(operationChooser);
        DesignClass.displaySecondPolynomial(operationChooser, secondPolynomialText, secondText);
        makeTheOperationButton.addActionListener(e -> {
            //perform solving
            Polynomial poly1 = new Polynomial(firstPolynomialText.getText());
            Polynomial poly2 = new Polynomial(secondPolynomialText.getText());
            String resultedPolynomialString = OperationClass.solveOperationForPolynomials(poly1, poly2, Objects.requireNonNull(operationChooser.getSelectedItem()).toString());
            finalText.setText(resultedPolynomialString);
        });
        setModal(true);
        setVisible(true);

    }
    public static void main(String[] args) {
        new GUI_Class();

    }
}
