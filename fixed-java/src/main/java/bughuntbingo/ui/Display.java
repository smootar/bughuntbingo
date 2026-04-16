package bughuntbingo.ui;

import bughuntbingo.utils.Format;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Display extends JPanel {

    private final JLabel expressionLabel;
    private final JLabel valueLabel;

    public Display() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0x0f3460));
        setBorder(new EmptyBorder(16, 20, 16, 20));
        setPreferredSize(new Dimension(280, 80));

        expressionLabel = new JLabel("\u00a0");
        expressionLabel.setForeground(new Color(0x8892b0));
        expressionLabel.setFont(expressionLabel.getFont().deriveFont(14f));
        expressionLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        valueLabel = new JLabel("0");
        valueLabel.setForeground(new Color(0xe6e6e6));
        valueLabel.setFont(new Font("Courier New", Font.BOLD, 32));
        valueLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(expressionLabel);
        add(valueLabel);
    }

    public void update(String expression, String value) {
        expressionLabel.setText(expression.isEmpty() ? "\u00a0" : expression);
        valueLabel.setText(Format.formatDisplay(value));
    }
}
