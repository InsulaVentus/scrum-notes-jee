package no.agricolas.scrumnotes.generatenote;

import no.agricolas.scrumnotes.jira.service.JiraService;
import no.agricolas.scrumnotes.jira.service.SimpleJiraService;
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
    private JiraService jiraService = new SimpleJiraService();
    private GeneratorService generatorService = new SimpleGeneratorService();

    private static final int GRID_WIDTH = 1;
    private static final int GRID_HEIGHT = 6;


    private JButton btnSave = new JButton("Generate");

    private JTextField taskName = new JTextField();

    private JLabel urlLabel = new JLabel("Task name");
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
            add(taskName);
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
        JFileChooser fileChooser = new JFileChooser();
        int rVal = fileChooser.showSaveDialog(GenerateNote.this);

        if (rVal == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().getName();
            String dir = fileChooser.getCurrentDirectory().toString();

            String path = dir + File.separator + filename;
            if (radioButtonYes.isSelected()) {
                System.out.println("PARENT");
                generateScrumnotesFromParent(path);
            } else {
                System.out.println("CHILD");
                generateSingleSrumnoteFromChild();
            }
        }
    }

    private void generateSingleSrumnoteFromChild() {
        
    }

    private void generateScrumnotesFromParent(String path) {
        ScrumNotesStub stub = new ScrumNotesStub();

        //simpleGeneratorService.createNotesFromSubtask(jiraService.getSubIssues(taskName.getText()), path);

        generatorService.createNotesFromSubtask(stub.getSubtasks(taskName.getText()), path);
    }
}
