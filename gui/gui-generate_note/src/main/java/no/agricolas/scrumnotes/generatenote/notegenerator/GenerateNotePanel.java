package no.agricolas.scrumnotes.generatenote.notegenerator;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import no.agricolas.scrumnotes.generatenote.notegenerator.utils.ScrumNotesStub;
import no.agricolas.scrumnotes.generatenote.notegenerator.utils.validator.TaskValidator;
import no.agricolas.scrumnotes.jira.service.JiraService;
import no.agricolas.scrumnotes.jira.service.SimpleJiraService;
import no.agricolas.srumnotes.common.GeneratorService;
import no.agricolas.srumnotes.common.SimpleGeneratorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * @author Simen Søhol
 */
public class GenerateNotePanel extends JPanel {
    private JiraService jiraService = new SimpleJiraService();
    private TaskValidator taskValidator = new TaskValidator();
    private DefaultListModel<String> loggingListModel;
    private GeneratorService generatorService = new SimpleGeneratorService();

    private JButton btnSave = new JButton("Generate");

    private JTextField taskName = new JTextField();

    private JLabel urlLabel = new JLabel("Task name");
    private JLabel parentLabel = new JLabel("Parent task");

    private JRadioButton radioButtonYes;

    private JRadioButton radioButtonNo;

    public GenerateNotePanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new NoteGeneratorPanel());
        add(new ButtonPanel());
        add(new LoggingPanel());
    }

    private class NoteGeneratorPanel extends JPanel {
        public NoteGeneratorPanel() {
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            JPanel taskNamePane = new JPanel();
            taskNamePane.setLayout(new BoxLayout(taskNamePane, BoxLayout.LINE_AXIS));
            taskNamePane.add(urlLabel);
            taskNamePane.add(Box.createRigidArea(new Dimension(10, 0)));
            taskName.setColumns(40);
            taskNamePane.add(taskName);
            taskNamePane.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

            JPanel radioPane = new JPanel();
            radioPane.setLayout(new BoxLayout(radioPane, BoxLayout.LINE_AXIS));

            radioPane.add(parentLabel);
            radioPane.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 440));
            createRadioGroup();
            radioPane.add(radioButtonYes);
            radioPane.add(radioButtonNo);

            add(taskNamePane);
            add(radioPane);
        }
    }

    private class ButtonPanel extends JPanel implements ActionListener {
        public ButtonPanel() {
            setLayout(new FlowLayout());
            btnSave.addActionListener(this);
            add(btnSave);
            setBorder(BorderFactory.createEmptyBorder(0, 0, 190, 0));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (taskValidator.validateTask(taskName.getText())) {
                saveParentDialog();
            }
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
        int rVal = fileChooser.showSaveDialog(GenerateNotePanel.this);

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

    private class LoggingPanel extends JPanel {
        public LoggingPanel() {
            setLayout(new GridLayout(1, 1));

            loggingListModel = new DefaultListModel<String>();

            JList<String> logList = new JList<String>(loggingListModel);
            add(new JScrollPane(logList));
        }
    }

    private void generateSingleSrumnoteFromChild() {

    }

    private void generateScrumnotesFromParent(String path) {
        ScrumNotesStub stub = new ScrumNotesStub();

        //simpleGeneratorService.createNotesFromSubtask(jiraService.getSubIssues(taskName.getText()), path);

        List<SubtaskNote> subtaskNoteList = stub.getSubtasks(taskName.getText());

        loggingListModel.addElement("Generating " + subtaskNoteList.size() + " subtasks from " + subtaskNoteList.get(0).getParentTask());

        generatorService.createNotesFromSubtask(subtaskNoteList, path);
    }
}
