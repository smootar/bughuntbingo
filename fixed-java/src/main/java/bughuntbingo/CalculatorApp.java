package bughuntbingo;

import bughuntbingo.ui.Calculator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculatorApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bug Hunt Bingo \u2014 Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            JPanel app = new JPanel(new BorderLayout());
            app.setBackground(new Color(0x1a1a2e));
            app.setBorder(new EmptyBorder(20, 20, 20, 20));
            app.add(new Calculator(), BorderLayout.CENTER);

            frame.setContentPane(app);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
