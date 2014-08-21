package no.agricolas.scrumnotes.generatenote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Simen Søhol
 */
public class Main extends JFrame {
    private static final int JFRAME_WIDTH_SIZE = 600;
    private static final int JFRAME_HEIGHT_SIZE = 450;

    private JButton btnExit = new JButton("Exit");

    public Main() {
        setTitle("SRUMNOTES");

        add(new TabFrame());
        add(new ButtonPanel(), BorderLayout.SOUTH);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(JFRAME_WIDTH_SIZE, JFRAME_HEIGHT_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class TabFrame extends JTabbedPane {
        private static final String TITLE_NOTE = "Generate Note";
        private static final String TITLE_BURNDOWN = "Generate Burndown";

        public TabFrame() {
            add(TITLE_NOTE, new GenerateNote());
            add(TITLE_BURNDOWN, new JPanel());
        }
    }

    private class ButtonPanel extends JPanel implements ActionListener {
        public ButtonPanel() {
            btnExit.addActionListener(this);
            add(btnExit);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
