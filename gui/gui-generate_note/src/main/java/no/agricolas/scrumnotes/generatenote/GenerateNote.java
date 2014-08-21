package no.agricolas.scrumnotes.generatenote;

import no.agricolas.srumnotes.common.GeneratorService;
import no.agricolas.srumnotes.common.SimpleGeneratorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Simen Søhol
 */
public class GenerateNote extends JPanel {
    private static final int GRID_WIDTH = 2;
    private static final int GRID_HEIGHT = 3;

    private SimpleGeneratorService simpleGeneratorService = new GeneratorService();

    private JButton btnSave = new JButton("Generate");
    private JButton btnExit = new JButton("Exit");
    private JTextField filename = new JTextField();
    private JTextField dir = new JTextField();
    private JTextField url = new JTextField();

    private JLabel urlLabel = new JLabel("URL til parent");
    private JLabel dirLabel = new JLabel("Filen er lagret på");
    private JLabel filenameLabel = new JLabel("Filnavn");

    public GenerateNote() {
        add(new FileInfoPanel(), BorderLayout.NORTH);
        add(new ButtonPanel(), BorderLayout.SOUTH);
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
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveDialog();
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
        ScrumNotesStub stub = new ScrumNotesStub();
        simpleGeneratorService.createNotesFromSubtask(stub.getSubtasks(url.getText()), path);
    }
}
