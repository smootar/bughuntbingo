package bughuntbingo.ui;

import javax.swing.*;
import java.awt.*;

public class MemoryIndicator extends JLabel {

    public MemoryIndicator() {
        setText("\u00a0");
        setForeground(new Color(0xe94560));
        setFont(getFont().deriveFont(Font.BOLD, 14f));
    }

    public void update(boolean hasMemory) {
        setText(hasMemory ? "M" : "\u00a0");
    }
}
