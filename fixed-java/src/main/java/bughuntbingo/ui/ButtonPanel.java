package bughuntbingo.ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ButtonPanel extends JPanel {

    private static final Object[][] BUTTONS = {
        {"MC","memory","memory-clear"},  {"MR","memory","memory-recall"},
        {"M+","memory","memory-add"},    {"M\u2212","memory","memory-sub"},
        {"C","clear","clear"},           {"\u232b","clear","backspace"},
        {"%","operator","percent"},      {"\u00f7","operator","/"},
        {"7","digit","7"},  {"8","digit","8"},  {"9","digit","9"},  {"\u00d7","operator","*"},
        {"4","digit","4"},  {"5","digit","5"},  {"6","digit","6"},  {"\u2212","operator","-"},
        {"1","digit","1"},  {"2","digit","2"},  {"3","digit","3"},  {"+","operator","+"},
        {"0","digit","0","wide"},  {".","digit","."},  {"=","equals","="},
    };

    public ButtonPanel(Consumer<String> onButton) {
        setLayout(new GridBagLayout());
        setBackground(new Color(0x16213e));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        c.ipady = 6;

        int col = 0, row = 0;
        for (Object[] btn : BUTTONS) {
            boolean wide = btn.length == 4 && "wide".equals(btn[3]);
            JButton b = makeButton((String) btn[0], (String) btn[1], (String) btn[2], onButton);
            c.gridx = col;
            c.gridy = row;
            c.gridwidth = wide ? 2 : 1;
            c.ipadx = wide ? 40 : 20;
            add(b, c);
            col += wide ? 2 : 1;
            if (col >= 4) { col = 0; row++; }
        }
    }

    private JButton makeButton(String label, String type, String action, Consumer<String> onButton) {
        JButton b = new JButton(label);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.setForeground(Color.WHITE);

        Color normal, hover;
        int fontSize = 18;
        switch (type) {
            case "operator": normal = new Color(0xe94560); hover = new Color(0xff6b81); break;
            case "equals":   normal = new Color(0x0f9b58); hover = new Color(0x12b86a); break;
            case "clear":    normal = new Color(0x533483); hover = new Color(0x6c44a2); break;
            case "memory":
                normal = new Color(0x1a1a3e); hover = new Color(0x2a2a5e);
                b.setForeground(new Color(0x8892b0));
                fontSize = 14;
                break;
            default: normal = new Color(0x1a1a3e); hover = new Color(0x2a2a5e); break;
        }
        b.setBackground(normal);
        b.setFont(b.getFont().deriveFont(Font.PLAIN, fontSize));

        Color finalNormal = normal, finalHover = hover;
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { b.setBackground(finalHover); }
            public void mouseExited(java.awt.event.MouseEvent e)  { b.setBackground(finalNormal); }
        });
        b.addActionListener(e -> onButton.accept(action));
        return b;
    }
}
