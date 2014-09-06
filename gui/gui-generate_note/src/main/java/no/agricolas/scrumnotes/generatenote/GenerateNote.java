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
    private static final int GRID_WIDTH = 1;
    private static final int GRID_HEIGHT = 6;

    private SimpleGeneratorService simpleGeneratorService = new GeneratorService();

    private JButton btnSave = new JButton("Generate");

    private JTextField url = new JTextField();

    private JLabel urlLabel = new JLabel("Task url");
    private JLabel parentLabel = new JLabel("Parent task");

    private JRadioButton radioButtonYes;

    private JRadioButton radioButtonNo;

    public GenerateNote() {
        setLayout(new GridLayout(2, 1));
        add(new TopPanel(), BorderLayout.NORTH);
        add(new ButtonPanel(), BorderLayout.CENTER);
    }

    private class TopPanel extends JPanel {
        public TopPanel() {
            setLayout(new GridLayout(GRID_HEIGHT, GRID_WIDTH));

            add(urlLabel);
            add(url);
            add(parentLabel);
            createRadioGroup();
            add(radioButtonYes);
            add(radioButtonNo);
        }
    }

    private class ButtonPanel extends JPanel implements ActionListener {
        public ButtonPanel() {
            setLayout(new FlowLayout());
            btnSave.addActionListener(this);
            add(btnSave);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveParentDialog();
        }
    }

    private void createRadioGroup() {
        radioButtonYes = new JRadioButton("Yes");
        radioButtonYes.setSelected(true);

        radioButtonNo = new JRadioButton("No");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonYes);
        buttonGroup.add(radioButtonNo);
    }

    private void saveParentDialog() {
        JFileChooser c = new JFileChooser();
        int rVal = c.showSaveDialog(GenerateNote.this);

        if (rVal == JFileChooser.APPROVE_OPTION) {
            String filename = c.getSelectedFile().getName();
            String dir = c.getCurrentDirectory().toString();

            String path = dir + File.separator + filename;
            if (radioButtonYes.isSelected()) {
                System.out.println("YES");
                generateScrumnotesFromParent(path);
            } else {
                System.out.println("NO");
            }
        }
    }

    private void generateScrumnotesFromParent(String path) {
        ScrumNotesStub stub = new ScrumNotesStub();
        simpleGeneratorService.createNotesFromSubtask(stub.getSubtasks(url.getText()), path);
    }
}
