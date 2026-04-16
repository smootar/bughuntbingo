package bughuntbingo.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryPanel extends JPanel {

    private final JPanel historyList;

    public HistoryPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(0x0f3460));
        setBorder(new EmptyBorder(16, 16, 16, 16));
        setPreferredSize(new Dimension(180, 420));

        JLabel title = new JLabel("HISTORY");
        title.setForeground(new Color(0x8892b0));
        title.setFont(title.getFont().deriveFont(Font.BOLD, 12f));
        add(title, BorderLayout.NORTH);

        historyList = new JPanel();
        historyList.setLayout(new BoxLayout(historyList, BoxLayout.Y_AXIS));
        historyList.setBackground(new Color(0x0f3460));

        JScrollPane scroll = new JScrollPane(historyList);
        scroll.setBorder(null);
        scroll.setBackground(new Color(0x0f3460));
        scroll.getViewport().setBackground(new Color(0x0f3460));
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);

        update(new ArrayList<>());
    }

    public void update(List<String> history) {
        historyList.removeAll();
        List<String> reversed = new ArrayList<>(history);
        Collections.reverse(reversed);
        if (reversed.isEmpty()) {
            JLabel empty = new JLabel("No calculations yet");
            empty.setForeground(new Color(0x4a5568));
            empty.setFont(empty.getFont().deriveFont(Font.ITALIC, 13f));
            historyList.add(empty);
        } else {
            for (String entry : reversed) {
                JLabel lbl = new JLabel(entry);
                lbl.setForeground(new Color(0xc0c8d8));
                lbl.setFont(new Font("Courier New", Font.PLAIN, 13));
                lbl.setBorder(new EmptyBorder(4, 6, 4, 6));
                historyList.add(lbl);
                historyList.add(Box.createVerticalStrut(4));
            }
        }
        historyList.revalidate();
        historyList.repaint();
    }
}
