package no.agricolas.scrumnotes.generatenote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author Simen Søhol
 */
public class GenerateNote extends JFrame {
    private static final int JFRAME_WIDTH_SIZE = 400;
    private static final int JFRAME_HEIGHT_SIZE = 250;
    private static final int GRID_WIDTH = 2;
    private static final int GRID_HEIGHT = 3;
    private static final String APPNAME = "Scrumnotes";

    private JButton btnSave = new JButton("Generate");
    private JButton btnExit = new JButton("Exit");
    private JTextField filename = new JTextField();
    private JTextField dir = new JTextField();
    private JTextField url = new JTextField();

    private JLabel urlLabel = new JLabel("URL til parent");
    private JLabel dirLabel = new JLabel("Filen er lagret på");
    private JLabel filenameLabel = new JLabel("Filnavn");

    public GenerateNote() {
        setTitle(APPNAME);
        add(new FileInfoPanel(), BorderLayout.NORTH);
        add(new ButtonPanel(), BorderLayout.SOUTH);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(JFRAME_WIDTH_SIZE, JFRAME_HEIGHT_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class FileInfoPanel extends JPanel {

        public FileInfoPanel() {
            dir.setEditable(false);
            filename.setEditable(false);
            setLayout(new GridLayout(GRID_HEIGHT, GRID_WIDTH));
            add(urlLabel);
            add(url);
            add(filenameLabel);
            add(filename);
            add(dirLabel);
            add(dir);
        }
    }

    private class ButtonPanel extends JPanel implements ActionListener {
        public ButtonPanel() {
            btnSave.addActionListener(this);
            btnExit.addActionListener(this);
            add(btnSave);
            add(btnExit);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (button == btnSave) {
                saveDialog();
            } else {
                System.exit(0);
            }
        }
    }

    private void saveDialog() {
        JFileChooser c = new JFileChooser();
        int rVal = c.showSaveDialog(GenerateNote.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            filename.setText(c.getSelectedFile().getName());
            dir.setText(c.getCurrentDirectory().toString());

            String path = dir.getText() + File.separator + filename.getText();
            generateScrumnote(path);
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
            filename.setText("You pressed cancel");
            dir.setText("");
        }
    }

    private void generateScrumnote(String path) {
        //call service
    }

    public static void main(String[] args) {
        new GenerateNote();
    }
}
